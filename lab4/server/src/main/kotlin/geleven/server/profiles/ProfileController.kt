package geleven.server.profiles

import org.springframework.web.bind.annotation.*

@RestController
class   ProfileController(private val profileService: ProfileService) {

    @GetMapping("/API/profiles/{email}")
    fun getProfile(@PathVariable email: String): ProfileDTO? {
        return profileService.getProfile(email)
    }

    @PostMapping("/API/profiles")
    fun createProfile(@RequestBody profile: Profile): ProfileDTO? {
        return profileService.createProfile(profile)
    }

    @PutMapping("/API/profiles/{email}")
    fun updateProfile(
        @PathVariable email: String,
        @RequestBody profile: Profile
    ): ProfileDTO? {
        return profileService.updateProfile(email, profile)
    }
}