package ADT;

public class TreeNode {

        public TreeNode left, right;
        public int height = 1;
        public long value;

        public TreeNode (long key) {
            this.value = key;
        }

		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

		public long getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
        
        
}
