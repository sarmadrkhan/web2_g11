package geleven.server.profiles

interface ProfileService {
//    GET /API/profiles/{email}
//    POST /API/profiles
//    PUT /API/profiles/{email}

    fun getProfile(email: String): ProfileDTO?
    fun createProfile(profile: Profile): ProfileDTO?
    fun updateProfile(email: String, profile: Profile): ProfileDTO?
}