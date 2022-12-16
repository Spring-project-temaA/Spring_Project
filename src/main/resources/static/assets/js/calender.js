$(document).ready(function () {
    $(function () {
        $('.datepicker').datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
            startDate: '0d',
            language: "kr",


        });
        $('#datepicker').datepicker('setDate', 'today');
    });
    function disOnOff() {
        if ($('#dp1').val() == '') {
            $('.cell').addClass("dis");
        } else {
            $('.cell').removeClass("dis");
        }
    }

    $(function () {
        if ($('#dp1').on.change) {
            if ($('#dp1').val() == '') {
                $('.cell').addClass("dis");
            } else {
                $('.cell').removeClass("dis");
            }
        }
    })

    $('.cell').click(function () {
        $('.cell').removeClass('select');
        $(this).addClass('select');
        var time = $('.select').text();

        $('#dp2').val(time);
    });
});

function sumbit() {
    $('#dp2').disable = false;
    $('#apointTime').val($('#dp2').val());
    $('#btn-apoint').attr("type", "submit");
    $('#btn-apoint').click(true);
}