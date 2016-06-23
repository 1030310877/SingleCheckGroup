# SingleCheckGroup
简化实现单选和不选状态的ViewGroup

场景描述：
一些情况下，需要对所列选项进行单选或不选，使用RadioGroup智能实现单选，切无法对布局进行自由化。<br>
该控件的编写主要解决以上两个问题。

###使用说明
1.SingleCheckGroup继承FrameLayout，会自动遍历所有子View（包括子ViewGroup中的View），通过Tag将实现了Checkable接口的控件记录下来。<br>
实现拥有用一Tag的控件之间的check互斥。
2.如果是自定义的View，则View需要实现Checkable接口，并在setCheck()方法最后调用callOnClick()。
3.被SingleCheckGroup记录下来的View，不能去实现OnClickListener监听，通过对SingleCheckGroup添加onGroupItemViewClick监听来替代。
4.group的单项选中状态和无选中状态切换，会有onCheckItemChangedListener监听。
