package geleven.server.profiles

import geleven.server.utils.DuplicateException
import geleven.server.utils.NotFoundException
import org.springframework.web.bind.annotation.*

@RestController
class   ProfileController(private val profileService: ProfileService) {

    @GetMapping("/API/profiles/{email}")
    fun getProfile(@PathVariable email: String): ProfileDTO? {
        val profile = profileService.getProfile(email)
        return profile ?: throw NotFoundException("Profile with email '$email' not found")
    }

    @PostMapping("/API/profiles/")
    fun createProfile(@RequestBody profile: Profile): ProfileDTO? {
        if (profileService.getProfile(profile.email) != null) {
            throw DuplicateException("Profile with email '${profile.email}' already exists")
        }
        return profileService.createProfile(profile)
    }

    @PutMapping("/API/profiles/{email}")
    fun updateProfile(
        @PathVariable email: String,
        @RequestBody profile: Profile
    ): ProfileDTO? {
        profile.email = email
        if(profileService.getProfile(email) == null) {
            throw NotFoundException("Profile with email '$email' not found")
        }
        return profileService.updateProfile(profile)
    }
}
