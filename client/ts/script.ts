// import UserApi from './UserApi';

// const API_BASE_URL = 'http://localhost:8080';

// const userApi = new UserApi(API_BASE_URL);

if (("serial" in navigator)) {
    let notSupportedNotification = document.querySelector('#notSupported');
    console.log(notSupportedNotification);
    console.log('Serial API supported.');
}


document.querySelector('#connect').addEventListener('click', async() => {
    const port = await navigator.serial.requestPort();
    console.log(port);
    port.getInfo();
});

// const ports = await navigator.serial.getPorts();

navigator.serial.getPorts().then((ports) => {
    // Initialize the list of available ports with `ports` on page load.
    console.log(ports)
  });