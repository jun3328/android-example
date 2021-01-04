package io.github.jesterz91.viewpagerindicator.gallery

//class GalleryFragment : Fragment() {
//
//    companion object {
//        fun newInstance(color: Int): GalleryFragment {
//            return GalleryFragment().apply {
//                arguments = bundleOf("color" to color)
//            }
//        }
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.item_gallery, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        val layout = view.findViewById<LinearLayout>(R.id.color_layout)
//        val color = arguments?.getInt("color", -1) ?: -1
//        color.takeUnless { it == -1 }?.run(layout::setBackgroundColor)
//    }
//}