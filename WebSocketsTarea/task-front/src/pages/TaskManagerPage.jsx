import { Button, Grid, Stack} from '@mui/joy';
import { useEffect, useState,useCallback } from 'react';
import ListTask from '../components/ListTask';
import { createTask, getTasksList } from '../services/taskService';
import useWebSocket from '../services/webSocketService';

const TaskManagerPage = () => {
  const [listTasks, setListTask] = useState([]);
  let count = 0;


  const ws = useWebSocket(() => {
    updateTask();

  });

  const updateTask = useCallback(() => {
    getTasksList().then((data) => {
      setListTask(data);
    });
  }, []);

  useEffect(() => {
    updateTask();

    return () => {
        ws.close();
    }
  }, [updateTask]);



  return (
    <Stack
      spacing={3}
      sx={{
        padding: 3,
        width: '100%',
        alignItems: 'center',
      }}
    >
      <Button
        color="primary"
        variant="solid"
        onClick={() => {
          const newTask = {
            name: 'Creada desde el front' + count++,
            description: 'Desc',
            notes: 'Ninguna',
            priority: 1,
            listId: 1,
          };
          createTask(newTask);
        }}
      >
        Crear Tarea
      </Button>

      <Grid
        container
        spacing={2}
        sx={{
          justifyContent: 'center',
          flexWrap: 'wrap',
          width: '100%',
        }}
      >
        {listTasks.map((l, i) => (
          <Grid key={i} item>
            <ListTask list={l} />
          </Grid>
        ))}
      </Grid>
    </Stack>
  );

};

export default TaskManagerPage;
