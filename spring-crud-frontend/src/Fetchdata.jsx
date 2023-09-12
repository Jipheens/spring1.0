import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'

function Fetchdata() {
    const [data,setdata]=useState([])
    useEffect(() => {
        fetch("http://localhost:7676/api/v1/getAll")
          .then((response) => response.json())
          .then((jsonResponse) => {
            const entityArray = jsonResponse.entity;
      
            setdata(entityArray);
            console.log("this is what I have fetched", entityArray);
          });
      }, []);
      
      const handleDelete = (id) => {
        // Make a DELETE request to the API endpoint with the id as a parameter
        fetch(`http://localhost:7676/api/v1/delete?id=${id}`, {
          method: 'DELETE',
        })
          .then((response) => {
            if (response.status === 200) {
              // If the deletion is successful, update the state to remove the deleted item
              setdata((prevData) => prevData.filter((item) => item.id !== id));
              console.log(`Item with id ${id} deleted successfully.`);
            } else {
              console.error(`Failed to delete item with id ${id}.`);
            }
          })
          .catch((error) => {
            console.error(`Error occurred while deleting item with id ${id}:`, error);
          });
      };
         
    
  return (
    <div>
        <p className='head'><h2>OUR FETCHED RECORDS FOR OUR MICROSERVICE</h2></p>
        <p><hr></hr></p>
        <Link to="/new">  <button className='btn btn-warning'  >NEW ITEM</button>
</Link>
        <p><hr></hr></p>
        <table className='table table-striped table-active table-hover'>
  <thead className="table-dark">
    <tr>
      <th>id</th>
      <th>age</th>
      <th>department</th>
      <th>dob</th>
      <th>fname</th>
      <th>lname</th>
      <th>Action</th>
    </tr>
  </thead>
  <tbody>
    {Array.isArray(data) ? (
      data.map(product => (
        <tr key={product.id}>
          <td>{product.age}</td>
          <td>{product.department}</td>
          <td>{product.dob}</td>
          <td>{product.fname}</td>
          <td>{product.lname}</td>
          <td>
            <Link to={"/"+product.itemCode+"/edit"}>
              <button className="btn btn-primary btn-sm">EDIT</button>
            </Link>
            <button  className="btn btn-danger btn-sm"  onClick={() => handleDelete(product.id)}>  DELETE</button>

          </td>
        </tr>
      ))
    ) : (
      <tr>
        <td colSpan="7">No data available</td>
      </tr>
    )}
  </tbody>
</table>

    </div>
  )
}

export default Fetchdata