/**
 * Set the progress bar color based on value.
 */

$(document).ready(function() {

  var bars = $('.progress');
  for (i = 0; i < bars.length; i++) {
    var progress = $(bars[i]).val();
    //$(bars[i]).width(progress + '%');
    //console.log($(bars[i]).val());
    if (progress >= "90") {
      $(bars[i]).addClass("is-success");
    } else if (progress >= "50" && progress < "90") {
      $(bars[i]).addClass("is-warning");
    } else {
      $(bars[i]).addClass("is-error");
    }
  }
});