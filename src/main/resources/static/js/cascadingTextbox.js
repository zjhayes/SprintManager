/**
 * Show/hide fields based on dropdown selection.
 */
$(document).ready(function(){
	$("#step3-label").show();
    $("#step3").show();
    $("#step4-label").hide();
    $("#step4").hide();
    $("#step5-label").hide();
    $("#step5").hide();
    $("#step6-label").hide();
    $("#step6").hide();
    $('#numSteps').on('change', function() {
      if (this.value == '2')
      {
    	$("#step3-label").hide();
        $("#step3").hide();
        $("#step4-label").hide();
        $("#step4").hide();
        $("#step5-label").hide();
        $("#step5").hide();
        $("#step6-label").hide();
        $("#step6").hide();
      }
      else if(this.value == '3')
      {
      	$("#step3-label").show();
        $("#step3").show();
        $("#step4-label").hide();
        $("#step4").hide();
        $("#step5-label").hide();
        $("#step5").hide();
        $("#step6-label").hide();
        $("#step6").hide();
      }
      else if(this.value == '4')
      {
    	$("#step3-label").show();
        $("#step3").show();
        $("#step4-label").show();
        $("#step4").show();
        $("#step5-label").hide();
        $("#step5").hide();
        $("#step6-label").hide();
        $("#step6").hide();
      }
      else if(this.value == '5')
      {
      	$("#step3-label").show();
        $("#step3").show();
        $("#step4-label").show();
        $("#step4").show();
        $("#step5-label").show();
        $("#step5").show();
        $("#step6-label").hide();
        $("#step6").hide();
      }
      else if(this.value == '6')
      {
    	$("#step3-label").show();
        $("#step3").show();
        $("#step4-label").show();
        $("#step4").show();
        $("#step5-label").show();
        $("#step5").show();
        $("#step6-label").show();
        $("#step6").show();
      }
    });
});