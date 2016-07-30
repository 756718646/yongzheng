### android开发笔录

### android Button上面的英文字符串自动大写的问题解决

资料链接:http://blog.csdn.net/ouyang_peng/article/details/50238459

Android 5.1的SDK把Button的默认Style改了，样式默认把textAllCaps设置为true了(设置为false后就不会)

<style name="TextAppearance.Material.Button">

        <item name="textSize">@dimen/text_size_button_material</item>
        
        <item name="fontFamily">@string/font_family_button_material</item>
        
        <item name="textAllCaps">true</item>
        
        <item name="textColor">?attr/textColorPrimary</item>
        
</style>

### 华为手机调试log不显示

华为Android手机打开Log, 显示日志
在用华为安卓手机开发时LogCat没有显示日志，在cmd里输入“adb logcat”，提示“Unable to open log device '/dev/log/main': No such file or directory”，可按以下步骤解决：
1）进入工程模式
   有两种方式可以进入工程模式：
     a. 在拨号界面输入“*#*#2846579#*#*”
     b. 若是小米4.0系统(MIUI)，进入“设置-->全部设置-->原厂设置-->工程模式”
         
(图1 工程模式界面)
2) 打开Log
    1. 依次进入“后台设置-->2.LOG设置-->LOG开关”，选择“LOG打开”；返回上一个界面，点击“LOG级别设置”，选择“VREBOSE”
    2. 返回到图1所示二面，选择“6. Dump & Log”,打开开关“打开Dump & Log”

3) 重启手机

From: http://hi.baidu.com/windgoing/item/507be1a9ac067d2d8919d3ea


