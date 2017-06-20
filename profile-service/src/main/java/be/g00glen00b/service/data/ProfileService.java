package be.g00glen00b.service.data;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.stream.Collectors;
import javax.sql.rowset.serial.SerialBlob;
import be.g00glen00b.service.ProfileAvatarFailedException;
import be.g00glen00b.service.ProfileAvatarInvalidException;
import be.g00glen00b.service.ProfileAvatarNotFoundException;
import be.g00glen00b.service.ProfileInvalidException;
import be.g00glen00b.service.ProfileNotFoundException;
import be.g00glen00b.service.web.model.NewProfileDTO;
import be.g00glen00b.service.web.model.ProfileDTO;
import be.g00glen00b.service.web.model.ProfilesDTO;
import be.g00glen00b.service.web.model.SimpleProfileDTO;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProfileService {
    private static final long FILE_SIZE_LIMIT = 1024 * 1024; // 1 Mb
    private ProfileRepository repository;

    @Autowired
    public ProfileService(ProfileRepository repository) {
        this.repository = repository;
    }

    public ProfilesDTO findAll(int offset, int limit) {
        Page<Profile> page = repository.findAll(new OffsetPageRequest(offset, limit));
        return new ProfilesDTO(offset, limit, page.getTotalElements(), page.getContent().stream()
            .map(SimpleProfileDTO::fromEntity)
            .collect(Collectors.toList()));
    }

    public ProfileDTO findOne(String username) {
        return ProfileDTO.fromEntity(repository.findOneOptional(username)
            .orElseThrow(ProfileNotFoundException::new));
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity updateAvatar(String username, MultipartFile file) {
        if (file.getSize() > FILE_SIZE_LIMIT) {
            throw new ProfileAvatarInvalidException("File is too large");
        } else if (!file.getContentType().startsWith("image/")) {
            throw new ProfileAvatarInvalidException("File can only be an image");
        }
        Profile profile = repository.findOneDetailedOptional(username).orElseThrow(ProfileNotFoundException::new);
        if (!profile.isCurrentUser()) {
            throw new ProfileAvatarInvalidException("You do not have the permission to change this avatar");
        }
        if (profile.getAvatar() != null) {
            profile.getAvatar().setProfile(null);
            profile.setAvatar(null);
            repository.flush();
        }
        profile.setAvatar(new ProfileAvatar(profile));
        try {
            profile.getAvatar().setAvatar(new SerialBlob(file.getBytes()));
            profile.getAvatar().setMime(file.getContentType());
        } catch (IOException | SQLException ex) {
            throw new ProfileAvatarFailedException("Could not update avatar", ex);
        }
        return getAvatarResponse(profile.getAvatar());
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ProfileDTO update(String username, ProfileDTO profile) {
        Profile entity = repository.findOneDetailedOptional(username).orElseThrow(ProfileNotFoundException::new);
        if (!entity.isCurrentUser()) {
            throw new ProfileInvalidException("You do not have the permission to change this avatar");
        }
        entity.setFirstname(profile.getFirstname());
        entity.setLastname(profile.getLastname());
        entity.setBio(profile.getBio());
        return ProfileDTO.fromEntity(entity);
    }

    @Transactional
    @PreAuthorize("isAuthenticated()")
    public ProfileDTO save(NewProfileDTO profile) {
        // Verifying if profile exists
        repository.findOneOptional(profile.getUsername()).ifPresent(found -> {throw new ProfileAlreadyExistsException();});
        repository.findByEmailOptional(profile.getEmail()).ifPresent(found -> {throw new ProfileAlreadyExistsException();});

        Profile entity = new Profile(profile.getEmail(), profile.getUsername(), profile.getFirstname(), profile.getLastname(), profile.getBio(), null);
        return ProfileDTO.fromEntity(repository.saveAndFlush(entity));
    }

    public ResponseEntity getAvatar(String username) {
        Profile profile = repository.findOneDetailedOptional(username)
            .orElseThrow(ProfileNotFoundException::new);
        ProfileAvatar avatar = profile.getAvatar();
        if (avatar != null) {
            return getAvatarResponse(avatar);
        } else {
            throw new ProfileAvatarNotFoundException("User does not have an avatar");
        }
    }

    private ResponseEntity getAvatarResponse(ProfileAvatar avatar) {
        InputStream inputStream;
        try {
            inputStream = avatar.getAvatar().getBinaryStream();
            return ResponseEntity.ok()
                .contentLength(avatar.getAvatar().length())
                .contentType(MediaType.parseMediaType(avatar.getMime()))
                .body(new InputStreamResource(inputStream));
        } catch (SQLException ex) {
            throw new ProfileAvatarFailedException("Could not retrieve avatar", ex);
        }
    }
}
