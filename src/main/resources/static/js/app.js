function postMessage() {
  var message = document.getElementById('message').value;
  fetch('/chat?message=' + encodeURIComponent(message), {
    method: 'POST'
  })
  .then(response => response.text())
  .then(data => {
    // handle the response from the server
    console.log('Response from ChatGPT API:', data);
        const chatBox = document.getElementById("chatBox");
        const messageWrapper = document.createElement("div");
        messageWrapper.classList.add("message-wrapper");

        const botMessage = document.createElement("div");
        botMessage.classList.add("message", "bot-message");
        botMessage.innerHTML = data;
        messageWrapper.appendChild(botMessage);

        chatBox.appendChild(messageWrapper);
        chatBox.scrollTop = chatBox.scrollHeight;
    })
  .catch(error => console.error(error));
}


function addMessageToChat(message) {
  var chat = document.getElementById('chat');
  var messageElem = document.createElement('div');
  messageElem.classList.add('message');
  messageElem.innerText = message;
  chat.appendChild(messageElem);
}

function handleMessageResponse(response) {
  var message = response.message;
  addMessageToChat(message);
}

function sendMessage() {
  postMessage();
}

function sendInput() {
  var input = document.getElementById("input").value;
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
      document.getElementById("response").innerHTML = this.responseText;
    }
  };
  xhttp.open("POST", "/api/yourEndpoint", true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send("input=" + input);
}

function displayResponse(response) {
    document.getElementById("response").innerHTML = response.data.choices[0].text;
}
document.addEventListener("DOMContentLoaded", function() {
  var form = document.getElementById('message-form');
  form.addEventListener('submit', function(event) {
    event.preventDefault();
    sendMessage();
  });
});
