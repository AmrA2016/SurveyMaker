$(document).on("click", ".move-up", function () {
    var $all_questions = $(".question");
    var $parent = $(this).parents(".question");
    var previous_index = $all_questions.index($parent) - 1;
    var $previous = $all_questions.eq(previous_index);

    if (previous_index >= 0) {

        var current_question_num = $all_questions.index($parent) + 1;
        var prev_question_num = $all_questions.index($previous) + 1;

        //We need to swap questions' number
        //Update Current question number
        var $question_type = $parent.find(".question-type");
        var $question_content = $parent.find(".question-content");

        $question_type.attr("name", "question" + prev_question_num + "_type")
        $question_content.attr("name", "question" + prev_question_num + "_content");

        if ($question_type.val() == "mcq" || $question_type.val() == "checkbox") {

            var $options_count = $parent.find(".options-count");
            var $all_options = $parent.find(".question-option");

            $options_count.attr("name", "question" + prev_question_num + "_options_count");

            $all_options.each(function (i) {
                $(this).attr("name", "question" + prev_question_num + "_option" + (i + 1));
            });
        }

        //Update previous question number
        var $question_type = $previous.find(".question-type");
        var $question_content = $previous.find(".question-content");

        $question_type.attr("name", "question" + current_question_num + "_type")
        $question_content.attr("name", "question" + current_question_num + "_content");

        if ($question_type.val() == "mcq" || $question_type.val() == "checkbox") {

            var $options_count = $previous.find(".options-count");
            var $all_options = $previous.find(".question-option");

            $options_count.attr("name", "question" + current_question_num + "_options_count");

            $all_options.each(function (i) {
                $(this).attr("name", "question" + current_question_num + "_option" + (i + 1));
            });
        }


        $parent.insertBefore($previous);
    }

});

$(document).on("click", ".move-down", function () {
    var $all_questions = $(".question");
    var $parent = $(this).parents(".question");
    var next_index = $all_questions.index($parent) + 1;
    var $next = $all_questions.eq(next_index);

    if (next_index < $all_questions.length) {

        var current_question_num = $all_questions.index($parent) + 1;
        var next_question_num = $all_questions.index($next) + 1;

        //We need to swap questions' number
        //Update Current question number
        var $question_type = $parent.find(".question-type");
        var $question_content = $parent.find(".question-content");

        $question_type.attr("name", "question" + next_question_num + "_type")
        $question_content.attr("name", "question" + next_question_num + "_content");

        if ($question_type.val() == "mcq" || $question_type.val() == "checkbox") {

            var $options_count = $parent.find(".options-count");
            var $all_options = $parent.find(".question-option");

            $options_count.attr("name", "question" + next_question_num + "_options_count");

            $all_options.each(function (i) {
                $(this).attr("name", "question" + next_question_num + "_option" + (i + 1));
            });
        }

        //Update previous question number
        var $question_type = $next.find(".question-type");
        var $question_content = $next.find(".question-content");

        $question_type.attr("name", "question" + current_question_num + "_type")
        $question_content.attr("name", "question" + current_question_num + "_content");

        if ($question_type.val() == "mcq" || $question_type.val() == "checkbox") {

            var $options_count = $next.find(".options-count");
            var $all_options = $next.find(".question-option");

            $options_count.attr("name", "question" + current_question_num + "_options_count");

            $all_options.each(function (i) {
                $(this).attr("name", "question" + current_question_num + "_option" + (i + 1));
            });
        }

        $parent.insertAfter($next);
    }
});

$(document).on("click", ".del-question", function () {
    var $questions_count = $('.questions-count');

    var $parent = $(this).parents(".question");

    $parent.remove();

    $questions_count.val(parseInt($questions_count.val()) - 1);

    var $all_questions = $(".question");

    $all_questions.each(function (i) {
        $(this).find(".question-content").attr("name", "question" + (i + 1) + "_content");
        $(this).find(".question-type").attr("name", "question" + (i + 1) + "_type");
        
        var type = $(this).find(".question-type").val();
        
        if(type == "mcq" || type == "checkbox"){
            $(this).find(".options-count").attr("name", "question" + (i+1) +"_options_count");
            var $all_options = $(this).find(".question-option");
            
            $all_options.each(function(j) {
               $(this).attr("name", "question" + (i+1) + "_option" + (j+1)); 
            });
        }
    });

});


$(document).on("click", ".add-option", function () {

    var $parent = $(this).parents(".question");
    var question_number = $(".question").index($parent) + 1;
    var $options_count = $parent.find(".options-count");

    var $all_options = $parent.find(".option-containter");
    var $last_option = $all_options.last();

    $options_count.val(parseInt($options_count.val()) + 1);
    $new_option = $('<div class="form-group row option-containter"> \
                        <div class="col-md-10"> \
                            <input type="text" class="form-control question-option" name="question' + question_number + '_option' + $options_count.val() + '" value="Option"> \
                        </div> \
                        <div class="col-md-2 option-buttons" > \
                            <a class="add-option "><i class="fa fa-plus-circle" aria-hidden="true"></i></a> \
                            <a class="del-option "><i class="fa fa-minus-circle" aria-hidden="true"></i></a> \
                        </div> \
                    </div>');

    $last_option.after($new_option);

});

$(document).on("click", ".del-option", function () {
    var $question = $(this).parents(".question");
    var $parent = $(this).parents(".option-containter");
    var $options_count = $question.find(".options-count");

    if (parseInt($options_count.val()) <= 1) {
        alert("Question must have 1 option at least");
    } else {
        $parent.remove();

        $options_count.val(parseInt($options_count.val()) - 1);

        var $all_options = $question.find(".question-option");
        $all_options.each(function (i) {
            var curr_name = $(this).attr("name");
            var new_name = curr_name.slice(0, -1) + (i + 1);
            $(this).attr("name", new_name);
        });

    }


});

$("#add-question-form").on("submit",function(event){
    event.preventDefault();
    var question_type = $(this).find('select[name="new_question_type"]').val();
    
    var questions_count = parseInt($(".questions-count").val());
    questions_count++;
    $(".questions-count").val(questions_count);
    
    if(question_type == "mcq"){
        var $new_question = $('<div class="question mcq-question"> \
                                <input type="text" class="question-type" name="question'+ questions_count + '_type" value="mcq" hidden="hidden"> \
                                <div class="form-group"> \
                                    <input type="text" class="form-control question-content" name="question' + questions_count +'_content" value="Untitled Question" required> \
                                </div> \
                                <input type="number" class="options-count" name="question' + questions_count + '_options_count" value="1" hidden="hidden"> \
                                <div class="form-group row option-containter"> \
                                    <div class="col-md-10"> \
                                        <input type="text" class="form-control question-option" name="question' + questions_count + '_option1" value="Option"> \
                                    </div> \
                                    <div class="col-md-2 option-buttons"> \
                                        <a class="add-option "><i class="fa fa-plus-circle" aria-hidden="true"></i></a> \
                                        <a class="del-option "><i class="fa fa-minus-circle" aria-hidden="true"></i></a>\
                                    </div> \
                                </div> \
                                <div class="question-buttons"> \
                                    <a href="#" class="move-up "><i class="fa fa-arrow-up" aria-hidden="true"></i></a> \
                                    <a href="#" class="move-down "><i class="fa fa-arrow-down" aria-hidden="true"></i></a> \
                                    <a href="#" class="del-question "><i class="fa fa-trash" aria-hidden="true"></i></a> \
                                </div> \
                            </div>');
        $(".questions").append($new_question);
    }
    else if(question_type == "checkbox"){
        var $new_question = $('<div class="question checkbox-question"> \
                                <input type="text" class="question-type" name="question'+ questions_count + '_type" value="checkbox" hidden="hidden"> \
                                <div class="form-group"> \
                                    <input type="text" class="form-control question-content" name="question' + questions_count +'_content" value="Untitled Question" required> \
                                </div> \
                                <input type="number" class="options-count" name="question' + questions_count + '_options_count" value="1" hidden="hidden"> \
                                <div class="form-group row option-containter"> \
                                    <div class="col-md-10"> \
                                        <input type="text" class="form-control question-option" name="question' + questions_count + '_option1" value="Option"> \
                                    </div> \
                                    <div class="col-md-2 option-buttons"> \
                                        <a class="add-option "><i class="fa fa-plus-circle" aria-hidden="true"></i></a> \
                                        <a class="del-option "><i class="fa fa-minus-circle" aria-hidden="true"></i></a>\
                                    </div> \
                                </div> \
                                <div class="question-buttons"> \
                                    <a href="#" class="move-up "><i class="fa fa-arrow-up" aria-hidden="true"></i></a> \
                                    <a href="#" class="move-down "><i class="fa fa-arrow-down" aria-hidden="true"></i></a> \
                                    <a href="#" class="del-question "><i class="fa fa-trash" aria-hidden="true"></i></a> \
                                </div> \
                            </div>');
        $(".questions").append($new_question);
    }
    else if(question_type == "open"){
        var $new_question = $('<div class="question open-question"> \
                                <input type="text" class="question-type" name="question'+ questions_count + '_type" value="open" hidden="hidden"> \
                                <div class="form-group"> \
                                    <input type="text" class="form-control question-content" name="question' + questions_count +'_content" value="Untitled Question" required> \
                                </div> \
                                <div class="form-group row"> \
                                    <div class="col-md-12"> \
                                        <input type="text" class="form-control question-answer" value="Open Answer" disabled="disabled"> \
                                    </div> \
                                </div> \
                                <div class="question-buttons"> \
                                    <a href="#" class="move-up "><i class="fa fa-arrow-up" aria-hidden="true"></i></a> \
                                    <a href="#" class="move-down "><i class="fa fa-arrow-down" aria-hidden="true"></i></a> \
                                    <a href="#" class="del-question "><i class="fa fa-trash" aria-hidden="true"></i></a> \
                                </div> \
                            </div>');
        $(".questions").append($new_question);
    }
})