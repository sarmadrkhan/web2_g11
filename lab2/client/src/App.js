import React, { useState, useEffect } from "react";
import API from "./API";

function App() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [profile, setProfile] = useState(null);

  useEffect(() => {
    // fetch all products on component mount
    API.getAllProducts().then((data) => setProducts(data));
  }, []);

  const handleProductSelect = (productId) => {
    // fetch selected product details
    API.getProduct(productId)
      .then((data) => setSelectedProduct(data))
      .catch((error) => console.log(error));
  };

  const handleProfileFetch = (email) => {
    // fetch user profile details
    API.getProfile("johndoe@gmail.com")
      .then((data) => setProfile(data))
      .catch((error) => console.log(error));
  };

  const handleProfileCreate = (profileData) => {
    // create new user profile
    API.createProfile(profileData)
      .then((data) => setProfile(data))
      .catch((error) => console.log(error));
  };

  const handleProfileUpdate = (email, profileData) => {
    // update existing user profile
    API.updateProfile(email, profileData)
      .then((data) => {
        console.log(data);
        setProfile(data);
      })
      .catch((error) => console.log(error));
  };

  return (
    <div className="App">
      <h1>Product List</h1>
      <ul>
        {products.map((product) => (
          <li
            key={product.ean}
            onClick={() => handleProductSelect(product.ean)}
          >
            {product.name}
          </li>
        ))}
      </ul>

      {selectedProduct && (
        <div>
          <h2>EAN: {selectedProduct.ean}</h2>
          <p>Name: {selectedProduct.name}</p>
          <p>Brand: {selectedProduct.brand}</p>
        </div>
      )}

      <h1>User Profile</h1>
      {profile ? (
        <div>
          <p>
            Name: {profile.firstName} {profile.lastName}
          </p>
          <p>Email: {profile.email}</p>
        </div>
      ) : (
        <div>
          <p>No profile found.</p>
          <button onClick={() => handleProfileFetch()}>
            Load John Doe's Profile
          </button>
        </div>
      )}

      <h1>Create or Update Profile</h1>
      <form onSubmit={(e) => e.preventDefault()}>
        <label>
          Name:
          <input type="text" name="first" />
        </label>
        <label>
          LastName:
          <input type="text" name="last" />
        </label>
        <br />
        <label>
          Email:
          <input type="email" name="email" />
        </label>
        <br />
        <button
          onClick={() =>
            handleProfileCreate({
              firstName: "Alice",
              lastName: "Doe",
              email: "alice@example.com",
            })
          }
        >
          Create Profile
        </button>
        <button
          onClick={() =>
            handleProfileUpdate("johndoe@gmail.com", {
              firstName: "sarmad",
              lastName: "khan",
            })
          }
        >
          Update Profile
        </button>
      </form>
    </div>
  );
}

export default App;
