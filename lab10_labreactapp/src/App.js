
import React, { useEffect, useState } from 'react';
import { createBrowserRouter, RouterProvider } from 'react-router-dom';
import axios from 'axios';
import ProductList from './ProductList';
import ProductDetail from './ProductDetail';

function App() {
  const [products, setProducts] = useState([]);

  useEffect(() => {
    axios.get('https://dummyjson.com/products')
      .then(response => setProducts(response.data.products))
      .catch(error => console.error(error));
  }, []);

  const router = createBrowserRouter([
    {
      path: '/',
      element: <ProductList products={products} />
    },
    {
      path: '/details/:id',
      element: <ProductDetail products={products} />
    }
  ]);

  return (
    <RouterProvider router={router} />
  );
}

export default App;
