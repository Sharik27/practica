const useWebSocket = (callback) => {
    const ws = new WebSocket("ws://10.147.17.101:8080/task-manager/A00399189/ws-connect?token=sadsad");
    
    ws.onopen = () => {
      console.log("Conectado");
      ws.send("Hola a todos!");
    };
    
    ws.onmessage = (event) => {
      console.log("Mensaje recibido:", event.data);
      callback(event.data)
    };
    return ws;
}
export default useWebSocket;
