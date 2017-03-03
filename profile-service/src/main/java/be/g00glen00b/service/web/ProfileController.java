package be.g00glen00b.service.web;

import be.g00glen00b.service.data.ProfileService;
import be.g00glen00b.service.web.model.ProfileDTO;
import be.g00glen00b.service.web.model.ProfilesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/profile")
public class ProfileController {
    private ProfileService service;

    @Autowired
    public ProfileController(ProfileService service) {
        this.service = service;
    }

    @GetMapping
    public ProfilesDTO findAll(
        @RequestParam(required = false, defaultValue = "0") int offset,
        @RequestParam(required = false, defaultValue = "10") int limit
    ) {
        return service.findAll(offset, limit);
    }

    @GetMapping("/{id}")
    public ProfileDTO findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PutMapping("/{id}/avatar")
    public ResponseEntity updateAvatar(@RequestParam MultipartFile avatar, @PathVariable Long id) {
        return service.updateAvatar(id, avatar);
    }

    @GetMapping("/{id}/avatar")
    public ResponseEntity getAvatar(@PathVariable Long id) {
        return service.getAvatar(id);
    }
}
