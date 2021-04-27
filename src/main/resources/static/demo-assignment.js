var ROOT_URL = "http://localhost:8080";
var statementList;
var mode = "I";

function getStatementData(){
    fetch(ROOT_URL+"/statement/")
        .then(function(response) {
            response.json().then(function(data) {
                statementList = data;

                let tableText = "<table border='1'><th>Date</th><th>Type</th><th>Amount</th><th>Description</th><th></th><th></th>";
                for (let index in data) {
                    tableText += "<tr>" +
                                    "<td>"+data[index].statementDate+"</td>" +
                                    "<td>"+(data[index].statementType == "I" ? "Income" : "Expense")+"</td>" +
                                    "<td>"+data[index].statementAmount+"</td>" +
                                    "<td>"+data[index].statementDesc+"</td>" +
                                    "<td><button onclick='editButtonOnClick("+index+")'>Edit</button></td>" +
                                    "<td><button onclick='deleteButtonOnClick("+index+")'>Delete</button></td>" +
                                  "</tr>";
                }
                tableText += "</table>"
                document.getElementById("content").innerHTML = tableText;
            });
        }).catch(function(err) {
            console.log('Exception ', err);
        }
    );
}

function saveButtonOnClick(){
    let formData;

    if(mode == "I") {
        formData= {
            statementDate: document.getElementById("statement-date").value,
            statementType: document.getElementById("statement-type").value,
            statementAmount: document.getElementById("amount").value,
            statementDesc: document.getElementById("description").value,
        }
    } else {
        formData= {
            statementId: document.getElementById("statement-id").value,
            statementDate: document.getElementById("statement-date").value,
            statementType: document.getElementById("statement-type").value,
            statementAmount: document.getElementById("amount").value,
            statementDesc: document.getElementById("description").value,
        }
    }

    fetch(ROOT_URL+"/statement/", {
        method: mode == "I" ? "POST" : "PUT",
        body: JSON.stringify(formData),
        headers: {
            'Content-Type': 'application/json'
        },
    }).then(response => {
        getStatementData();
        resetButtonOnClick();
    });
}

function editButtonOnClick(index){
    mode = "U";

    document.getElementById("statement-id").value = statementList[index].statementId;
    document.getElementById("statement-date").value = statementList[index].statementDate.slice(0, 10);
    document.getElementById("statement-type").value = statementList[index].statementType;
    document.getElementById("amount").value = statementList[index].statementAmount;
    document.getElementById("description").value = statementList[index].statementDesc;
}

function deleteButtonOnClick(index){
  if (confirm("Are you sure you want to delete : "+statementList[index].statementDate.slice(0, 10))) {
    fetch(ROOT_URL+"/statement/" + statementList[index].statementId, {
      method: 'DELETE',
    })
    .then(response => {
        getStatementData();
    });
  }
}

function resetButtonOnClick(){
    mode = "I";

    document.getElementById("statement-id").value = "";
    document.getElementById("statement-date").value = "";
    document.getElementById("statement-type").value = "";
    document.getElementById("amount").value = "";
    document.getElementById("description").value = "";
}

window.onload = getStatementData();