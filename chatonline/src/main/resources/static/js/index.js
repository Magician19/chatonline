/**
 * Created by chenyi on 2018/1/23.
 */

//打赏作者
function reward() {
    layui.use('layer', function () {
        var layer = layui.layer;

        layer.open({
            title: '',
            type: 1,
            area: ['600px', '448px'], //宽高
            content: '<img src="/img/reward.png">'
        });
    });

}

//公众号
function publics() {
    layui.use('layer', function () {
        var layer = layui.layer;

        layer.open({
            title: '',
            type: 1,
            area: ['258px', '278px'], //宽高
            content: '<span style="margin-left:25px;color:#5fb878">微信扫一扫，关注作者最新动态</span><br><img src="/img/cy.jpg">'
        });
    });


}



function download() {
    layui.use('layer', function () {
        var layer = layui.layer;

        layer.open({
            title: '关于',
            type: 1,
            area: ['600px', '190px'], //宽高
            content: [
                '<table class="layui-table" style="margin:10px;width: 580px">',
                '<colgroup>',
                '<col width="100">',
                '<col width="480">',
                '<col>',
                '</colgroup>',
                '<tbody>',
                '<tr>',
                '<td>项目说明</td>',
                '<td>本项目为本人学习netty时写的小demo! 欢迎体验<a target="_blank"  href="http://im.cymall.xin"  style="color:deepskyblue">二维社区版</a></td>',
                '</tr>',
                '<tr>',
                '<td>使用技术</td>',
                '<td>netty + webSocket + springboot + freemarker + layui </td>',
                '</tr>',
                '<tr>',
                '<td>交流下载</td>',
                '<td>1.q群下载 275846351 <a target="_blank" href="//shang.qq.com/wpa/qunwpa?idkey=5b2ec31ee55abc44722cf8b2c7f7807d5b44d9a08da06de2c589c305e4742364"><img border="0" src="//pub.idqqimg.com/wpa/images/group.png" alt="cy-security" title="cy-security"></a> ',
                '&nbsp;&nbsp;&nbsp;2.<a target="_blank" href="https://gitee.com/leiyuxi/cy-chat-room" style="color:deepskyblue">码云下载</a></td>',
                '</tr>',
                '</tbody>',
                '</table>'
            ].join("")

        });
    });


}
