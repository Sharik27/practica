import { Card,Typography,Stack } from "@mui/joy";
import Task from "./Task";

const ListTask = ({list}) => {
    return (
        <Card
            variant="outlined"
            sx={{
                backgroundColor: '#ffffff',
                padding: 2,
                borderRadius: 3,
                boxShadow: '0 4px 12px rgba(0,0,0,0.1)',
                minWidth: 280,
                maxWidth: 300,
            }}
        >
            <Typography level="h5" sx={{ marginBottom: 2, fontWeight: 600 }}>
                {list.name}
            </Typography>

            <Stack spacing={2}>
                {list.tasks.map((t, i) => (
                <Task key={i} task={t} />
                ))}
            </Stack>
        </Card>

    );


};

export default ListTask;