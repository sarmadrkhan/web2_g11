package geleven.server.profiles

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "profiles")
class Profile {
    @Id
    var email: String = ""

    var firstName: String = ""
    var lastName: String = ""
    var password: String = ""
    var salt: String = ""
}