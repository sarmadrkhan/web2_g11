package geleven.server.profiles

data class ProfileDTO(
    val email: String,
    val firstName: String,
    val lastName: String
)

fun Profile.toDTO(): ProfileDTO = ProfileDTO(
    email,
    firstName,
    lastName
)