$(function(){
    // 기존 로직 유지
    $('.cm_list')
        .on('click', 'a', function(){
            $(this).next('.hide_view').toggleClass('open');
        })
        .on('click', '.btns.icon_modify', function(){
            const id = $(this).closest('[data-comment-id]').data('comment-id');
            openCommentUpdatePopup(id);
        })
        .on('click', '.btns.icon_del', function(){
            const id = $(this).closest('[data-comment-id]').data('comment-id');
            deleteComment(id);
        });

    function wrapOverlay($layer){
        if (!$layer.parent().hasClass('overlay_t')){
            $layer.css({width: '100%', 'max-width': '500px'}).wrap('<div class="overlay_t"></div>');
        }
    }

    window.layerPop = function(id){
        const $layer = $('#'+id);
        wrapOverlay($layer);
        $layer.fadeIn(200);
        $('body').css('overflow','hidden');
    };

    window.layerPopClose = function(id){
        const $layer = id ? $('#'+id) : $('.popLayer');
        $layer.fadeOut(200, function(){
            if ($(this).parent().hasClass('overlay_t')){
                $(this).unwrap();
            }
        });
        $('body').css('overflow','auto');
    };
});