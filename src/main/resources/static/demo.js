var ROOT_URL = "http://localhost:8080";
var customerList;
var mode = "I";

function getCustomerData(){
    fetch(ROOT_URL+"/customer/")
        .then(function(response) {
            response.json().then(function(data) {
                customerList = data;

                let tableText = "<table border='1'><th>Customer name</th><th>Birthdate</th><th>Address</th><th>Active flag</th><th></th><th></th>";
                for (let index in data) {
                    tableText += "<tr>" +
                                    "<td>"+data[index].customerName+"</td>" +
                                    "<td>"+data[index].birthdate+"</td>" +
                                    "<td>"+data[index].address+"</td>" +
                                    "<td>"+(data[index].activeFlag == "Y" ? "yes" : "no")+"</td>" +
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
            customerName: document.getElementById("customer-name").value,
            birthdate: document.getElementById("birthdate").value,
            address: document.getElementById("address").value,
            activeFlag: document.getElementById("active-flag").value,
        }
    } else {
        formData= {
            customerId: document.getElementById("customer-id").value,
            customerName: document.getElementById("customer-name").value,
            birthdate: document.getElementById("birthdate").value,
            address: document.getElementById("address").value,
            activeFlag: document.getElementById("active-flag").value,
        }
    }

    fetch(ROOT_URL+"/customer/", {
        method: mode == "I" ? "POST" : "PUT",
        body: JSON.stringify(formData),
        headers: {
            'Content-Type': 'application/json'
        },
    }).then(response => {
//        console.log(response.json());

        getCustomerData();
        resetButtonOnClick();
    });
}

function editButtonOnClick(index){
    mode = "U";

    document.getElementById("customer-id").value = customerList[index].customerId;
    document.getElementById("customer-name").value = customerList[index].customerName;
    document.getElementById("birthdate").value = customerList[index].birthdate.slice(0, 10);
    document.getElementById("address").value = customerList[index].address;
    document.getElementById("active-flag").value = customerList[index].activeFlag;
}

function deleteButtonOnClick(index){
  if (confirm("Are you sure you want to delete : "+customerList[index].customerName)) {
    fetch(ROOT_URL+"/customer/" + customerList[index].customerId, {
      method: 'DELETE',
    })
    .then(response => {
//        console.log(response.json());
        getCustomerData();
    });
  }
}

function resetButtonOnClick(){
    mode = "I";

    document.getElementById("customer-id").value = "";
    document.getElementById("customer-name").value = "";
    document.getElementById("birthdate").value = "";
    document.getElementById("address").value = "";
    document.getElementById("active-flag").value = "";
}

window.onload = getCustomerData();