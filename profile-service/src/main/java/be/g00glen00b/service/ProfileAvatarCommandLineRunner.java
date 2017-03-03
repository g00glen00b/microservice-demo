package be.g00glen00b.service;

import javax.sql.rowset.serial.SerialBlob;
import be.g00glen00b.service.data.Profile;
import be.g00glen00b.service.data.ProfileAvatar;
import be.g00glen00b.service.data.ProfileRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ProfileAvatarCommandLineRunner implements CommandLineRunner {
    private ProfileRepository repository;

    public ProfileAvatarCommandLineRunner(ProfileRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public void run(String... strings) throws Exception {
        Profile profile = repository.findOne(1L);
        ClassPathResource classPathResource = new ClassPathResource("logo.png");
        profile.setAvatar(new ProfileAvatar(profile, new SerialBlob(IOUtils.toByteArray(classPathResource.getURL())), "image/png"));
    }
}
