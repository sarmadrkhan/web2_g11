import React, { useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import "../App.css";
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
      <Row>
        <Col>
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
        <Col>
          <ProfileData profile={props.profile} />
        </Col>
      </Row>
    </Container>
  );
};

const ProfileData = (props) => {
  return (
    <>
      {props.profile && (
        <Row className="profile-data">
          <Row>
            <h4>Profile Details</h4>
            <h5>
              {props.profile.firstName} {props.profile.lastName}
            </h5>
          </Row>
          <Row>
            <p>
              First Name: {props.profile.firstName}
              <br />
              Last Name: {props.profile.lastName}
              <br />
              Email: {props.profile.email}
              <br />
            </p>
          </Row>
        </Row>
      )}
    </>
  );
};

export default UserProfile;
