package be.g00glen00b.service.web;

import be.g00glen00b.service.data.ProfileService;
import be.g00glen00b.service.web.model.ProfileDTO;
import be.g00glen00b.service.web.model.ProfilesDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @GetMapping("/{username}")
    public ProfileDTO findOne(@PathVariable String username) {
        return service.findOne(username);
    }

    @GetMapping("/@me")
    public ProfileDTO findOne() {
        return service.findOne();
    }

    @PostMapping()
    public ProfileDTO save(@RequestBody ProfileDTO profile) {
        return service.save(profile);
    }

    @PutMapping("/@me")
    public ProfileDTO update(@RequestBody ProfileDTO profile) {
        return service.update(profile);
    }

    @PutMapping("/@me/avatar")
    public ResponseEntity updateAvatar(@RequestParam MultipartFile avatar) {
        return service.updateAvatar(avatar);
    }

    @GetMapping("/{username}/avatar")
    public ResponseEntity getAvatar(@PathVariable String username) {
        return service.getAvatar(username);
    }
}
