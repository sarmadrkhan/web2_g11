import React, { useState, useEffect } from "react";
import { Form, Button, Col, Row, Alert, ListGroup } from "react-bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";

import API from "./API";

function App() {
  const [products, setProducts] = useState([]);
  const [selectedProduct, setSelectedProduct] = useState(null);
  const [profile, setProfile] = useState(null);
  const [formData, setFormData] = useState({});
  const [createSuccess, setCreateSuccess] = useState(false);
  const [updateSuccess, setUpdateSuccess] = useState(false);
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
      .then((data) => {
        setProfile(data);
        setCreateSuccess(true);
        setTimeout(() => setCreateSuccess(false), 5000);
      })
      .catch((error) => console.log(error));
  };

  const handleProfileUpdate = (profileData) => {
    // update existing user profile
    API.updateProfile(profileData)
      .then((data) => {
        setProfile(data);
        setUpdateSuccess(true);
        setTimeout(() => setUpdateSuccess(false), 5000);
      })
      .catch((error) => console.log(error));
  };

  return (
    <div className="App">
      <h3>Product List</h3>
      <ListGroup>
        {products.map((product) => (
          <ListGroup.Item key={product.ean}>
            <Button
              onClick={() => handleProductSelect(product.ean)}
              variant="outline-primary"
            >
              {product.name}
            </Button>
          </ListGroup.Item>
        ))}
      </ListGroup>

      {selectedProduct && (
        <div>
          <h2>EAN: {selectedProduct.ean}</h2>
          <p>Name: {selectedProduct.name}</p>
          <p>Brand: {selectedProduct.brand}</p>
        </div>
      )}

      <h3>User Profile</h3>
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
          <Button onClick={() => handleProfileFetch()}>
            Load John Doe's Profile
          </Button>
        </div>
      )}

      <h3>Create or Update Profile</h3>
      <Form
        onSubmit={(e) => {
          e.preventDefault();
        }}
      >
        <Row>
          <Col>
            <Form.Label>First Name:</Form.Label>
            <Form.Control
              type="text"
              name="first"
              onChange={(e) =>
                setFormData({ ...formData, firstName: e.target.value })
              }
            />
          </Col>
          <Col>
            <Form.Label>Last Name:</Form.Label>
            <Form.Control
              type="text"
              name="last"
              onChange={(e) =>
                setFormData({ ...formData, lastName: e.target.value })
              }
            />
          </Col>
        </Row>
        <Row>
          <Col>
            <Form.Label>Email:</Form.Label>
            <Form.Control
              type="email"
              name="email"
              onChange={(e) =>
                setFormData({ ...formData, email: e.target.value })
              }
            />
          </Col>
        </Row>
        <br />
        <Button
          variant="primary"
          onClick={() => {
            handleProfileCreate(formData);
          }}
        >
          Create Profile
        </Button>
        &nbsp;
        <Button
          variant="primary"
          onClick={() => {
            handleProfileUpdate(formData);
          }}
        >
          Update Profile
        </Button>
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
      </Form>
    </div>
  );
}

export default App;
