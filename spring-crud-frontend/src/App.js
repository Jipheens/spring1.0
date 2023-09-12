import './App.css';
import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Routes } from "react-router-dom";

import Fetchdata from './Fetchdata';

function App() {
  return (
<Router>

<Routes>
  <Route path='/' element={<Fetchdata />}  />
  {/* <Route path='/:id/edit' element={<New title="Update Record" />}  /> */}
  {/* <Route path='/new' element={<New title="Add new Item"/>}  /> */}

</Routes>
      </Router>
  );
}

export default App;
