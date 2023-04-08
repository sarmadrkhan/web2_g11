import React, { useState, useEffect } from "react";
import { Alert, Container, Row } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

import API from "./API";
import ProductTable from "./components/ProductTable";
import UserProfile from "./components/UserProfile";
import CreateUpdateProfile from "./components/CreateUpdateProfile";

function App() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [profile, setProfile] = useState(null);
  const [alert, setAlert] = useState(null);

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
      .catch((error) => {
        setAlert({
          message: "Failed to fetch product",
          type: "danger",
        });
        setTimeout(() => setAlert(null), 5000);
        console.error(error);
      });
  };

  const handleProfileFetch = (email) => {
    // fetch user profile details
    API.getProfile(email)
      .then((data) => setProfile(data))
      .catch((error) => {
        setAlert({
          message: "Failed to fetch profile",
          type: "danger",
        });
        setTimeout(() => setAlert(null), 5000);
        console.error(error);
      });
  };

  const handleProfileCreate = (profileData) => {
    // create new user profile
    API.createProfile(profileData)
      .then((data) => {
        setProfile(data);
        setAlert({
          message: "Profile created successfully!",
          type: "success",
        });
        setTimeout(() => setAlert(null), 5000);
      })
      .catch((error) => {
        setAlert({
          message: "Failed to create profile",
          type: "danger",
        });
        setTimeout(() => setAlert(null), 5000);
        console.error(error);
      });
  };

  const handleProfileUpdate = (profileData) => {
    // update existing user profile
    API.updateProfile(profileData)
      .then((data) => {
        setProfile(data);
        setAlert({
          message: "Profile updated successfully!",
          type: "success",
        });
        setTimeout(() => setAlert(null), 5000);
      })
      .catch((error) => {
        setAlert({
          message: "Failed to update profile",
          type: "danger",
        });
        setTimeout(() => setAlert(null), 5000);
        console.error(error);
      });
  };

  return (
    <Container className="App">
      {alert && (
        <Alert
          variant={alert.type}
          onClose={() => setAlert(null)}
          dismissible
          className="fixed-top"
        >
          {alert.message}
        </Alert>
      )}
      <Row>
        <ProductTable
          products={products}
          handleProductSelect={handleProductSelect}
          selectedProduct={selectedProduct}
        />
      </Row>
      <Row>
        <UserProfile
          handleProfileFetch={handleProfileFetch}
          profile={profile}
        />
      </Row>
      <Row>
        <CreateUpdateProfile
          handleProfileCreate={handleProfileCreate}
          handleProfileUpdate={handleProfileUpdate}
        />
      </Row>
    </Container>
  );
}

export default App;
