import React, { useState, useEffect } from "react";
import { Alert, Container } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

import API from "./API";
import ProductTable from "./components/ProductTable";
import UserProfile from "./components/UserProfile";
import CreateUpdateProfile from "./components/CreateUpdateProfile";

function App() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [profile, setProfile] = useState(null);
  const [createSuccess, setCreateSuccess] = useState(false);
  const [updateSuccess, setUpdateSuccess] = useState(false);
  useEffect(() => {
    // fetch all products on component mount
    API.getAllProducts()
      .then((data) => setProducts(data))
      .catch((error) => {
        console.error("Error Fetching Products:", error);
      });
  }, []);

  const handleProductSelect = (productId) => {
    // fetch selected product details
    API.getProduct(productId)
      .then((data) => setSelectedProduct(data))
      .catch((error) => console.error(error));
  };

  const handleProfileFetch = (email) => {
    // fetch user profile details
    API.getProfile(email)
      .then((data) => setProfile(data))
      .catch((error) => console.error(error));
  };

  const handleProfileCreate = (profileData) => {
    // create new user profile
    API.createProfile(profileData)
      .then((data) => {
        setProfile(data);
        setCreateSuccess(true);
        setTimeout(() => setCreateSuccess(false), 5000);
      })
      .catch((error) => console.error(error));
  };

  const handleProfileUpdate = (profileData) => {
    // update existing user profile
    API.updateProfile(profileData)
      .then((data) => {
        setProfile(data);
        setUpdateSuccess(true);
        setTimeout(() => setUpdateSuccess(false), 5000);
      })
      .catch((error) => console.error(error));
  };

  return (
    <Container className="App">
      <ProductTable
        products={products}
        handleProductSelect={handleProductSelect}
        selectedProduct={selectedProduct}
      />

      <UserProfile handleProfileFetch={handleProfileFetch} profile={profile} />
      {createSuccess && (
        <Alert
          variant="success"
          onClose={() => setCreateSuccess(false)}
          dismissible
        >
          Profile created successfully!
        </Alert>
      )}
      {updateSuccess && (
        <Alert
          variant="success"
          onClose={() => setUpdateSuccess(false)}
          dismissible
        >
          Profile updated successfully!
        </Alert>
      )}

      <CreateUpdateProfile
        handleProfileCreate={handleProfileCreate}
        handleProfileUpdate={handleProfileUpdate}
      />
    </Container>
  );
}

export default App;
