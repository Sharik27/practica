import { Card, Typography,Stack,Button} from "@mui/joy";
import {updateTask} from "../services/taskService";
const Task = ({task}) => {
    const moveTask = (newListId) => {
    const updated = { ...task, listId: newListId };
    updateTask(updated);
  };

    return (
      <Card
        variant="soft"
        sx={{
          padding: 1.5,
          backgroundColor: '#f9f9f9',
          borderRadius: 2,
          boxShadow: 'sm',
          display: 'flex',
          flexDirection: 'column',
          gap: 1
        }}
      >
        <Typography level="body-md" fontWeight="md">
          {task.name}
        </Typography>
        <Stack direction="row" spacing={1} justifyContent="center">
          <Button size="sm" variant="solid" color="danger" onClick={() => moveTask(1)}>To Do</Button>
          <Button size="sm" color="warning" onClick={() => moveTask(2)}>Doing</Button>
          <Button size="sm" color="success" onClick={() => moveTask(3)}>Done</Button>
        </Stack>
      </Card>

);

};

export default Task;