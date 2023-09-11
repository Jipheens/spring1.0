import React, { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import axios from 'axios'

function Fetchdata() {
    const [data,setdata]=useState([])
    useEffect(() =>{
    fetch("http://localhost:7676/api/v1/getAll").then((response) => response.json()).then((jsonresponce) =>{
    setdata (jsonresponce)
    })

    },[])

    async function handleDelete(itemCode){
      try {
        await axios.delete("http://localhost:7676/api/v1/delete"+id)

      } catch (error) {
        console.log(error)
      }

    } 
    
  return (
    <div>
        <p className='head'><h2>OUR FETCHED RECORDS FOR OUR MICROSERVICE</h2></p>
        <p><hr></hr></p>
        <Link to="/new">  <button className='btn btn-warning'  >NEW ITEM</button>
</Link>
        <p><hr></hr></p>
 <table className='table table table-striped table-active table-hover'>
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
        {data.map(product => (
          <tr key={product.id}>
            <td>{product.age}</td>
            <td>{product.department}</td>
            <td>{product.dob}</td>
            <td>{product.fname}</td>
            <td>{product.lname}</td>
                        <td>
            <Link to={"/"+product.itemCode+"/edit"}><button className="btn btn-primary btn-sm"  >EDIT</button></Link>
            <button className="btn btn-danger btn-sm"  onClick={()=>handleDelete(product.itemCode)}>DELETE</button>
            </td>
          </tr>
        ))}
      </tbody>
    </table>






    </div>
  )
}

export default Fetchdata