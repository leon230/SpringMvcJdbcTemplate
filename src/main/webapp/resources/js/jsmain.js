jQuery(document).ready(function($) {

	$('#msg').html("This is updated by jQuery")
//    var table = document.getElementById('mainTable');
//    var tbody = table.getElementsByTagName('tbody')[0];
//    var cells = tbody.getElementsByTagName('td');
//    var cells = tbody.getElementById('td');

//    for (var i=0, len=cells.length; i<len; i++){
//        if (parseInt(cells[i].innerHTML,10) > 5){
//            cells[i].style.backgroundColor = 'red';
////            cells[i].className = 'red';
//        }
//        else if (parseInt(cells[i].innerHTML,10) < -5){
//            cells[i].style.backgroundColor = 'green';
//        }
//    }

});

function resetFilter()
{

$('#msg').html("This is updated by jQuery madafuckeirer")
 var cbs = document.getElementsByTagName('input');
 for(var i=0; i < cbs.length; i++)
 {
    if(cbs[i].type == 'checkbox')
    {
        cbs[i].checked = true;
//        cbs[i].checked = bx.checked;
     }
 }
}
function showTicketActions(){
    var ticketDet = document.getElementById('actionsDetails');

    $('#actionsDetails').toggle();
//$('#msg').html("This is updated by jQuery2")
// $('#msg').toggle();
}