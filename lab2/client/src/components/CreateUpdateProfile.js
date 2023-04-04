import React, { useState } from "react";
import { Button, Col, Form, Row } from "react-bootstrap";

const CreateUpdateProfile = (props) => {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    email: "",
  });
  return (
    <>
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
          variant="success"
          onClick={() => {
            props.handleProfileCreate(formData);
          }}
        >
          Create Profile
        </Button>
        &nbsp;
        <Button
          variant="warning"
          onClick={() => {
            props.handleProfileUpdate(formData);
          }}
        >
          Update Profile
        </Button>
      </Form>
    </>
  );
};

export default CreateUpdateProfile;
