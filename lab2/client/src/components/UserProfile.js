import React, { useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";

const UserProfile = (props) => {
  const [email, setEmail] = useState("");
  const handleEmailChange = (e) => {
    setEmail(e.target.value);
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!email) {
      alert("Please enter an email address.");
      return;
    }
    props.handleProfileFetch(email);
  };

  return (
    <Container className="mt-5 mb-5">
      <Row style={{ justifyContent: "space-between" }}>
        <Col md={6}>
          <h3>User Profile</h3>
          <p>Enter an existing email address:</p>
          <Form onSubmit={handleSubmit}>
            <Form.Group controlId="email">
              <Form.Control
                type="email"
                placeholder="Enter a valid email"
                value={email}
                onChange={handleEmailChange}
                required
              />
            </Form.Group>
            <Button variant="info" className="mt-2" type="submit">
              Load Profile
            </Button>
          </Form>
        </Col>
        <Col md={6} style={{ backgroundColor: "#DCDCDC", borderRadius: "5px" }}>
          {props.profile ? (
            <div>
              <h4>Profile Details</h4>
              <h5>
                {props.profile.firstName} {props.profile.lastName}
              </h5>
              <p>
                First Name: {props.profile.firstName}
                <br />
                Last Name: {props.profile.lastName}
                <br />
                Email: {props.profile.email}
                <br />
              </p>
            </div>
          ) : (
            <div>
              <h4>Profile Details</h4>
              <p>No profile found.</p>
            </div>
          )}
        </Col>
      </Row>
    </Container>
  );
};

export default UserProfile;
