### android开发笔录

###### android Button上面的英文字符串自动大写的问题解决

资料链接:http://blog.csdn.net/ouyang_peng/article/details/50238459

Android 5.1的SDK把Button的默认Style改了，样式默认把textAllCaps设置为true了(设置为false后就不会)

<style name="TextAppearance.Material.Button">

        <item name="textSize">@dimen/text_size_button_material</item>
        
        <item name="fontFamily">@string/font_family_button_material</item>
        
        <item name="textAllCaps">true</item>
        
        <item name="textColor">?attr/textColorPrimary</item>
        
</style>



