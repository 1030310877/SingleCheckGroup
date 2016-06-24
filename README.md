# SingleCheckGroup
简化实现单选和不选状态的ViewGroup

###场景描述：
一些情况下，需要对所列选项进行单选或不选，使用RadioGroup只能实现单选，且无法对布局进行自由化。<br>
该控件的编写主要解决以上两个问题。

###使用说明
1.SingleCheckGroup继承FrameLayout(可以改为继承其他布局)，会自动遍历所有子View（包括子ViewGroup中的View），因此，<br>内部的CheckBox可以完全自由布局。
2.通过view的tag属性，将实现了Checkable接口的CheckBox记录下来，实现拥有用一Tag的CheckBox之间的check互斥。
3.被SingleCheckGroup记录下来的CheckBox，`不能去实现OnClickListener监听`，通过对SingleCheckGroup添加onGroupItemViewClick<br>监听来替代。
4.group的单项选中状态和无选中状态切换，会有onCheckItemChangedListener监听，返回处于check状态的view，如果未选中<br>，则返回null。
5.不要使用CheckBox的setCheck方法，使用setCheckedItem进行CheckBox的状态设定。


###缺陷
缺陷太多了，目前子View只支持CheckBox，正在为支持实现了Checkable的自定义View以及减少使用限制努力。
