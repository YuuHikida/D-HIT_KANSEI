const elem = document.getElementById("add-todo");
const todoField = document.getElementById("todo-field");
let taskIndex /*[[${taskLogs.size()}]]*/;

// タスクの入力を追加します。
elem.onclick = function() {
    const node = document.createElement("div");
    node.setAttribute("class", "row");
    node.innerHTML += `<div class="flex-row d-flex gap-3 col-md-8">
                                <input type="text" class="form-control" placeholder="タスク名" aria-label=""
                                       name="${'taskLogs[' + taskIndex + '].name'}" required>
                                <input type="number" class="form-control" placeholder="進捗率" aria-label=""
                                       name="${'taskLogs[' + taskIndex + '].progressRate'}" min="0" max="100" required>
                                <button type="button" class="btn btn-danger" onclick="removeTask(this)">削除</button>
                            </div>`;

    todoField.appendChild(node);
    taskIndex++;
};

// タスクの入力を削除します。
function removeTask(button) {
    button.parentNode.parentNode.remove();
}
/*]]>*/

// 遅刻のcheckbokにvalidationをチェックします。
document.addEventListener('DOMContentLoaded', function() {
    var isLateness = document.getElementById('check_Lateness');
    var text_latenessReason = document.getElementById('text_latenessReason');

    isLateness.addEventListener('change', function() {
        if (this.checked) {
            validateInput();
        } else {
            text_latenessReason.setCustomValidity("");
        }
    });
    text_latenessReason.addEventListener('input', function() {
        if (isLateness.checked) {
            validateInput();
        }
    });
    function validateInput() {
        if (text_latenessReason.value.trim() === '') {
            text_latenessReason.setCustomValidity("遅刻理由を記入してください");
        } else {
            text_latenessReason.setCustomValidity("");
        }
    }
});
