/**
 *
 * @param {string} email email address of user
 * @param {string} firstName firstName of the user
 * @param {string} lastName lastName of the user
 */
function Profile({ email, firstName, lastName } = {}) {
  this.email = email;
  this.firstName = firstName;
  this.lastName = lastName;
}

exports.Profile = Profile;
