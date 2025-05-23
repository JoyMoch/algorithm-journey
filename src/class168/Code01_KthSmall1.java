package class168;

// 子矩阵第k小，java版
// 测试链接 : https://www.luogu.com.cn/problem/P1527
// 提交以下的code，提交时请把类名改成"Main"，可以通过所有测试用例

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;

public class Code01_KthSmall1 {

	public static int MAXN = 501;
	public static int MAXQ = 1000001;
	public static int INF = 1000000001;
	public static int n, q;

	// 矩阵中的每个数字，所在行x、所在列y、数值v
	public static int[][] xyv = new int[MAXN * MAXN][3];
	public static int cntv = 0;
	public static int used = 0;

	// 查询任务的编号
	public static int[] qid = new int[MAXQ];
	// 查询范围的左上角坐标
	public static int[] a = new int[MAXQ];
	public static int[] b = new int[MAXQ];
	// 查询范围的右下角坐标
	public static int[] c = new int[MAXQ];
	public static int[] d = new int[MAXQ];
	// 查询任务的第几小值
	public static int[] k = new int[MAXQ];

	// 二维树状数组
	public static int[][] tree = new int[MAXN][MAXN];

	// 整体二分
	public static int[] lset = new int[MAXQ];
	public static int[] rset = new int[MAXQ];

	// 每条查询的答案
	public static int[] ans = new int[MAXQ];

	public static int lowbit(int i) {
		return i & -i;
	}

	public static void add(int x, int y, int v) {
		for (int i = x; i <= n; i += lowbit(i)) {
			for (int j = y; j <= n; j += lowbit(j)) {
				tree[i][j] += v;
			}
		}
	}

	public static int query(int x, int y) {
		int ret = 0;
		for (int i = x; i > 0; i -= lowbit(i)) {
			for (int j = y; j > 0; j -= lowbit(j)) {
				ret += tree[i][j];
			}
		}
		return ret;
	}

	public static int query(int a, int b, int c, int d) {
		return query(c, d) - query(a - 1, d) - query(c, b - 1) + query(a - 1, b - 1);
	}

	public static void compute(int ql, int qr, int vl, int vr) {
		if (ql > qr) {
			return;
		}
		if (vl == vr) {
			for (int i = ql; i <= qr; i++) {
				ans[qid[i]] = vl;
			}
		} else {
			int mid = (vl + vr) >> 1;
			int lsiz = 0, rsiz = 0;
			while (used + 1 <= cntv && xyv[used + 1][2] <= mid) {
				used++;
				add(xyv[used][0], xyv[used][1], 1);
			}
			while (used >= 1 && xyv[used][2] > mid) {
				add(xyv[used][0], xyv[used][1], -1);
				used--;
			}
			for (int i = ql; i <= qr; i++) {
				int id = qid[i];
				int check = query(a[id], b[id], c[id], d[id]);
				if (check >= k[id]) {
					lset[++lsiz] = id;
				} else {
					rset[++rsiz] = id;
				}
			}
			for (int i = 1; i <= lsiz; i++) {
				qid[ql + i - 1] = lset[i];
			}
			for (int i = 1; i <= rsiz; i++) {
				qid[ql + lsiz + i - 1] = rset[i];
			}
			compute(ql, ql + lsiz - 1, vl, mid);
			compute(ql + lsiz, qr, mid + 1, vr);
		}
	}

	public static void main(String[] args) throws Exception {
		FastReader in = new FastReader(System.in);
		PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));
		n = in.nextInt();
		q = in.nextInt();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				xyv[++cntv][0] = i;
				xyv[cntv][1] = j;
				xyv[cntv][2] = in.nextInt();
			}
		}
		for (int i = 1; i <= q; i++) {
			qid[i] = i;
			a[i] = in.nextInt();
			b[i] = in.nextInt();
			c[i] = in.nextInt();
			d[i] = in.nextInt();
			k[i] = in.nextInt();
		}
		Arrays.sort(xyv, 1, cntv + 1, (a, b) -> a[2] - b[2]);
		compute(1, q, 0, INF);
		for (int i = 1; i <= q; i++) {
			out.println(ans[i]);
		}
		out.flush();
		out.close();
	}

	// 读写工具类
	static class FastReader {
		private final byte[] buffer = new byte[1 << 16];
		private int ptr = 0, len = 0;
		private final InputStream in;

		FastReader(InputStream in) {
			this.in = in;
		}

		private int readByte() throws IOException {
			if (ptr >= len) {
				len = in.read(buffer);
				ptr = 0;
				if (len <= 0)
					return -1;
			}
			return buffer[ptr++];
		}

		int nextInt() throws IOException {
			int c;
			do {
				c = readByte();
			} while (c <= ' ' && c != -1);
			boolean neg = false;
			if (c == '-') {
				neg = true;
				c = readByte();
			}
			int val = 0;
			while (c > ' ' && c != -1) {
				val = val * 10 + (c - '0');
				c = readByte();
			}
			return neg ? -val : val;
		}
	}

}
