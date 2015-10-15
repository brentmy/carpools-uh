(function($) {

  // activate tooltips
  $('[data-toggle="tooltip"]').tooltip();

  // filtering
  $('.filter-locations input').change(function(e) {
    var locations = [];
    $('.filter-locations input').each(function(i, el) {
      if($(el).prop('checked')) {
        locations.push($(el).attr('data-location'));
      }
    });

    if(locations.length > 0) {
      $('.drivers .driver').each(function(i, el) {
        var origin = $(el).attr('data-origin');
        if(locations.indexOf(origin) >= 0) {
          $(el).show();
        } else {
          $(el).hide();
        }
      }); 
    } else {
      $('.drivers .driver').show();
    }
  });

  // show modal on page load
  $('.modal.visible').modal('show');

  // image previewer
  $('.image-previewer').each(function(i, el) {
    var link = $(el).find('.image-input');
    var img = $(el).find('img');
    var fileInput = $(el).find('input[type="file"]');
    var hiddenInput = $(el).find('input[type="hidden"]');
    var removeImage = $(el).find('.remove-image');

    link.click(function(e) {
      e.preventDefault();
      fileInput.one('change', function() {
        if(this.files && this.files[0]) {
          var reader = new FileReader();
          reader.onload = function(e) {
            img.attr('src', e.target.result);
            hiddenInput.val("exists");
            removeImage.prop('checked', false);
          }
          reader.readAsDataURL(this.files[0]);
        }
      }).click();
    });

    removeImage.change(function(e) {
      var checked = $(this).prop('checked');
      if(checked) {
        img.data('save', img.attr('src'));
        img.attr('src', img.attr('data-default'));
        hiddenInput.val("");
      } else {
        img.attr('src', img.data('save'));
        hiddenInput.val(img.data('save'));
      }
    });
  });

  // popovers
  $('.timepicker').each(function(i, el) {
    var toggle = $(el).find('.toggle');
    var input = $(el).find('input[type="hidden"]');
    var popover = $(el).find('.popover');
    var hours = popover.find('.hours');
    var minutes = popover.find('.minutes');
    var ampm = popover.find('.ampm');

    var hidePopover = function() {
      popover.removeClass('in');
      setTimeout(function() {
        popover.hide();
      }, 400);
    }

    toggle.click(function(e) {
      e.preventDefault();
      if(!popover.hasClass('in')) {
        popover.show()
        popover.css({
          left: ($(el).outerWidth() - popover.outerWidth()) / 2,
          top: -popover.outerHeight()
        });
        setTimeout(function() {
          popover.addClass('in');  
        }, 0); 

        if(input.val()) {
          var value = input.val();
          var split = value.split(':');
          console.log(hours.val());
          hours.find('input').val(split[0]);
          split = split[1].split(' ');
          minutes.find('input').val(split[0]);
          ampm.find('input').val(split[1]);
        }
      } else {
        hidePopover();
      }
    });

    var setup = function(node, defaultValue, onIncrement, onDecrement) {
      var input = node.find('input');
      input.val(defaultValue);
      node.find('.up').click(function(e) {
        e.preventDefault();
        input.val(onIncrement(input.val()));
      });
      if(!onDecrement) onDecrement = onIncrement;
      node.find('.down').click(function(e) {
        e.preventDefault();
        input.val(onDecrement(input.val()));
      });
    };

    setup(hours, '1', function(currValue) {
      var val = Number(currValue) + 1;
      if(val == 13)
        return 1;
      return val;
    }, function(currValue) {
      var val = Number(currValue) - 1;
      if(val == 0)
        return 12;
      return val;
    });

    setup(minutes, '00', function(currValue) {
      var val = Number(currValue) + 5;
      if(val > 59)
        return "00";
      if(val < 10)
        return '0' + val;
      return val;
    }, function(currValue) {
      var val = Number(currValue) - 5;
      if(val < 0)
        return 55;
      if(val < 10)
        return '0' + val;
      return val;
    });

    setup(ampm, 'AM', function(currValue) {
      if(currValue == 'AM')
        return 'PM';
      return 'AM';
    });

    var setTimeBtn = popover.find('.set-time');
    var clearBtn = popover.find('.clear');
    var closeBtn = popover.find('.close');
    var hidePopover = function() {
      popover.removeClass('in');
      setTimeout(function() {
        popover.hide();
      }, 400);
    }

    setTimeBtn.click(function(e) {
      e.preventDefault();
      var value = hours.find('input').val() + ':' + minutes.find('input').val() + ' ' + ampm.find('input').val();
      toggle.text(value);
      input.val(value);
      hidePopover();
    });
    clearBtn.click(function(e) {
      e.preventDefault();
      toggle.text(toggle.attr('data-default'));
      input.val('');
      hidePopover();
    });
    closeBtn.click(function(e) {
      e.preventDefault();
      hidePopover();
    });
  });
  

})(jQuery);