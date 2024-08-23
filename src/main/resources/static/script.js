// script.js

// 모달 요소 가져오기
const modal = document.getElementById('gptModal');
const openModalButton = document.getElementById('openModal');
const gptInput = document.getElementById('gptInput');
const gptResponse = document.getElementById('gptResponse');
const sendCommandButton = document.getElementById('sendCommand');

// 모달 열기 함수
function openModal() {
    modal.style.display = "block";
    gptInput.focus();
}

// 모달 닫기 함수
function closeModal() {
    modal.style.display = "none";
}

// 백틱 키를 눌렀을 때 모달 열기
document.addEventListener('keydown', function(event) {
    if (event.key === '`') {
        openModal();
    }
});

// 명령어 전송 함수
async function sendCommand() {
    const command = gptInput.value;

    try {
        const response = await fetch('https://api.openai.com/v1/chat/completions', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '  // 실제 API 키로 대체
            },
            body: JSON.stringify({
                model: "gpt-4",
                messages: [
                    {"role": "system", "content": "You are a helpful assistant that detects user intentions."},
                    {"role": "user", "content": `User said: "${command}". Does the user want to write a post? Respond with 'yes' or 'no'.`}
                ],
                max_tokens: 10
            })
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${response.status}`);
        }

        const data = await response.json();

        const gptText = data.choices[0].message.content.trim().toLowerCase();

        if (gptText.includes("yes")) {
            window.location.href = "/board/save";  // 게시판 글 작성 페이지로 이동
        } else {
            // 사용자가 입력한 명령어에 대해 추가로 GPT와 대화를 나눕니다.
            const chatResponse = await fetch('https://api.openai.com/v1/chat/completions', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': 'Bearer '  // 실제 API 키로 대체
                },
                body: JSON.stringify({
                    model: "gpt-4",
                    messages: [
                        {"role": "system", "content": "You are a helpful assistant."},
                        {"role": "user", "content": command}
                    ],
                    max_tokens: 100
                })
            });

            if (!chatResponse.ok) {
                throw new Error(`HTTP error! status: ${chatResponse.status}`);
            }

            const chatData = await chatResponse.json();
            const chatText = chatData.choices[0].message.content.trim();

            gptResponse.innerText = "GPT Response: " + chatText;
        }

    } catch (error) {
        console.error('Error:', error);
        gptResponse.innerText = "An error occurred: " + error.message;
    }
}



// 버튼 클릭 시 명령어 전송
sendCommandButton.addEventListener('click', sendCommand);

// 모달 외부 클릭 시 모달 닫기
window.onclick = function(event) {
    if (event.target == modal) {
        closeModal();
    }
};
