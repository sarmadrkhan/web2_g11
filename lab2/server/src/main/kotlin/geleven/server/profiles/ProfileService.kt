package geleven.server.profiles

interface ProfileService {
    fun getProfile(email: String): ProfileDTO?
    fun createProfile(profile: Profile): ProfileDTO?
    fun updateProfile(profile: Profile): ProfileDTO?
}