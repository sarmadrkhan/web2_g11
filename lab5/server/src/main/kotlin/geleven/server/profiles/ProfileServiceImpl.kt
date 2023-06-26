package geleven.server.profiles

import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(private val profileRepository: ProfileRepository) :
    ProfileService {

    override fun getProfile(email: String): ProfileDTO? {
        return profileRepository.findAll().find { it.email == email }?.toDTO()
    }

    override fun createProfile(profile: Profile): ProfileDTO? {
        return profileRepository.save(profile).toDTO()
    }

    override fun updateProfile(email: String, profile: Profile): ProfileDTO? {
        val profileRep = profileRepository.findAll()
        val profileToUpdate = profileRep.find { it.email == email }
        profileToUpdate?.let {
            it.firstName = profile.firstName
            it.lastName = profile.lastName
            profileRepository.save(it)
            return it.toDTO()
        }
        return null
    }
}