import { axiosInstance } from './axiosService';

export const getTasksList = () => {
  return axiosInstance.get('/api/tasks-list').then((response) => {
    console.log(response.data)
    return response.data; 
  });
};

export const createTask = (task) => {
  return axiosInstance.post('/api/tasks', task).then((response) => {
    console.log(response.data);
    return response.data;
  });
}

export const updateTask = (task) => {
  return axiosInstance.put(`/api/tasks`, task).then((response) => {
    console.log(response.data);
    return response.data;
  });
};