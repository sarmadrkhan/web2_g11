const API_URL = "/API"; // proxy to server-side API

const API = {
  getAllProducts: async () => {
    const response = await fetch(`${API_URL}/products/`);
    if (!response.ok) {
      throw new Error(
        `API getAllProducts failed with status ${response.status}`
      );
    }
    const data = await response.json();
    return data;
  },

  getProduct: async (productId) => {
    const response = await fetch(`${API_URL}/products/${productId}`);
    if (!response.ok) {
      throw new Error(`API getProduct failed with status ${response.status}`);
    }
    const data = await response.json();
    return data;
  },

  getProfile: async (email) => {
    const response = await fetch(`${API_URL}/profiles/${email}`);
    if (!response.ok) {
      throw new Error(`API getProfile failed with status ${response.status}`);
    }
    const data = await response.json();
    return data;
  },

  createProfile: async (profileData) => {
    const response = await fetch(`${API_URL}/profiles`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(profileData),
    });
    if (!response.ok) {
      throw new Error(
        `API createProfile failed with status ${response.status}`
      );
    }
    const data = await response.json();
    return data;
  },

  updateProfile: async (email, profileData) => {
    const response = await fetch(`${API_URL}/profiles/${email}`, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(profileData),
    });
    if (!response.ok) {
      throw new Error(
        `API updateProfile failed with status ${response.status}`
      );
    }
    const data = await response.json();
    return data;
  },
};

export default API;
