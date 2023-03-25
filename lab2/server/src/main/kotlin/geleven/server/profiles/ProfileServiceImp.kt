package geleven.server.profiles

import org.springframework.stereotype.Service

@Service
class ProfileServiceImp(private val profileRepository: ProfileRepository) :
    ProfileService {

    override fun getProfile(email: String): ProfileDTO? {
        return profileRepository.findAll().find { it.email == email }?.toDTO()
    }

    override fun createProfile(profile: Profile): String? {
        profileRepository.save(profile).toDTO()
        return profile.email
    }

    override fun updateProfile(email: String, profile: Profile): String? {
        val profileRep = profileRepository.findAll()
        val profileToUpdate = profileRep.find { it.email == email }
        profile.email = email
        profileToUpdate?.let {
            profile.email = it.email
            profileRepository.save(profile)
        }
        return profileToUpdate?.email
    }
}