/*
 * 创建日期 2005-4-8
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.speedy.base.util;

/**
 * @author Administrator
 * 
 *         更改所生成类型注释的模板为 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class DES {
	public DES(long l) {
		subkey1 = new int[16];
		subkey2 = new int[16];
		setKey(l);
	}

	public long bytes2long(byte abyte0[]) {
		long l = 0L;
		for (int i = 0; i <= 7; i++)
			l = l << 8 | abyte0[i] & 255L;

		return l;
	}

	public long decrypt(long l) {
		l = ip(l);
		int i = (int) (l >>> 32);
		int j = (int) l;
		for (int k = 15; k >= 0; k--) {
			int i1 = i;
			i = j;
			j = i1;
			int j1 = (j >>> 3 | j << 29) ^ subkey1[k];
			int k1 = (j << 1 | j >>> 31) ^ subkey2[k];
			i ^= spbox[0][j1 >>> 24 & 0x3f] | spbox[1][k1 >>> 24 & 0x3f] | spbox[2][j1 >>> 16 & 0x3f]
					| spbox[3][k1 >>> 16 & 0x3f] | spbox[4][j1 >>> 8 & 0x3f] | spbox[5][k1 >>> 8 & 0x3f]
					| spbox[6][j1 & 0x3f] | spbox[7][k1 & 0x3f];
		}

		l = (long) i << 32 | j & 0xffffffffL;
		return rip(l);
	}

	public long encrypt(long l) {
		l = ip(l);
		int i = (int) (l >>> 32);
		int j = (int) l;
		for (int k = 0; k <= 15; k++) {
			int i1 = (j >>> 3 | j << 29) ^ subkey1[k];
			int j1 = (j << 1 | j >>> 31) ^ subkey2[k];
			i ^= spbox[0][i1 >>> 24 & 0x3f] | spbox[1][j1 >>> 24 & 0x3f] | spbox[2][i1 >>> 16 & 0x3f]
					| spbox[3][j1 >>> 16 & 0x3f] | spbox[4][i1 >>> 8 & 0x3f] | spbox[5][j1 >>> 8 & 0x3f]
					| spbox[6][i1 & 0x3f] | spbox[7][j1 & 0x3f];
			int k1 = i;
			i = j;
			j = k1;
		}

		l = (long) i << 32 | j & 0xffffffffL;
		return rip(l);
	}

	private long ip(long l) {
		long l1 = (l << 36 ^ l) & 0xf0f0f0f000000000L;
		l ^= l1 >>> 36 | l1;
		l1 = (l << 48 ^ l) & 0xffff000000000000L;
		l ^= l1 >>> 48 | l1;
		l1 = (l << 30 ^ l) & 0x3333333300000000L;
		l ^= l1 >>> 30 | l1;
		l1 = (l << 24 ^ l) & 0xff00ff00000000L;
		l ^= l1 >>> 24 | l1;
		l1 = (l << 33 ^ l) & 0xaaaaaaaa00000000L;
		l ^= l1 >>> 33 | l1;
		return l;
	}

	public void long2bytes(long l, byte abyte0[]) {
		for (int i = 7; i >= 0; i--) {
			abyte0[i] = (byte) (int) l;
			l >>>= 8;
		}

	}

	private long rip(long l) {
		long l1 = (l << 33 ^ l) & 0xaaaaaaaa00000000L;
		l ^= l1 >>> 33 | l1;
		l1 = (l << 24 ^ l) & 0xff00ff00000000L;
		l ^= l1 >>> 24 | l1;
		l1 = (l << 30 ^ l) & 0x3333333300000000L;
		l ^= l1 >>> 30 | l1;
		l1 = (l << 48 ^ l) & 0xffff000000000000L;
		l ^= l1 >>> 48 | l1;
		l1 = (l << 36 ^ l) & 0xf0f0f0f000000000L;
		l ^= l1 >>> 36 | l1;
		return l;
	}

	public void setKey(long l) {
		long l1 = (l << 36 ^ l) & 0xf0f0f0f000000000L;
		l ^= l1 >>> 36 | l1;
		l1 = (l << 18 ^ l) & 0xcccc0000cccc0000L;
		l ^= l1 | l1 >>> 18;
		l1 = (l << 9 ^ l) & 0xaa00aa00aa00aa00L;
		l ^= l1 | l1 >>> 9;
		int i = (int) (l >>> 28 & 0xffffff0L | l >>> 24 & 15L);
		int j = (int) (l << 20 & 0xff00000L | l << 4 & 0xff000L | l >>> 12 & 4080L | l >>> 28 & 15L);
		for (int k = 0; k < 16; k++) {
			if (ks[k]) {
				j = (j << 2 | j >>> 26) & 0xfffffff;
				i = (i << 2 | i >>> 26) & 0xfffffff;
			} else {
				j = (j << 1 | j >>> 27) & 0xfffffff;
				i = (i << 1 | i >>> 27) & 0xfffffff;
			}
			int i1 = kp[0][j >>> 22 & 0x3f] | kp[1][j >>> 16 & 0x30 | j >>> 15 & 0xf]
					| kp[2][j >>> 9 & 0x3c | j >>> 8 & 0x3] | kp[3][j >>> 3 & 0x20 | j >>> 1 & 0x18 | j & 0x7];
			int j1 = kp[4][j >>> 22 & 0x3f] | kp[5][j >>> 15 & 0x30 | j >>> 14 & 0xf] | kp[6][j >>> 7 & 0x3f]
					| kp[7][j >>> 1 & 0x3c | j & 0x3];
			subkey1[k] = i1 & 0xff000000 | (i1 & 0xff00) << 8 | (j1 & 0xff000000) >>> 16 | (j1 & 0xff00) >>> 8;
			subkey2[k] = (i1 & 0xff0000) << 8 | (i1 & 0xff) << 16 | (j1 & 0xff0000) >>> 8 | j1 & 0xff;
		}

	}

	private int subkey1[];
	private int subkey2[];
	private static final boolean ks[] = { false, false, true, true, true, true, true, true, false, true, true, true,
			true, true, true, false };
	private static final int kp[][] = {
			{ 0, 0x40000, 0x1000000, 0x1040000, 1024, 0x40400, 0x1000400, 0x1040400, 0x200000, 0x240000, 0x1200000,
					0x1240000, 0x200400, 0x240400, 0x1200400, 0x1240400, 1, 0x40001, 0x1000001, 0x1040001, 1025,
					0x40401, 0x1000401, 0x1040401, 0x200001, 0x240001, 0x1200001, 0x1240001, 0x200401, 0x240401,
					0x1200401, 0x1240401, 0x2000000, 0x2040000, 0x3000000, 0x3040000, 0x2000400, 0x2040400, 0x3000400,
					0x3040400, 0x2200000, 0x2240000, 0x3200000, 0x3240000, 0x2200400, 0x2240400, 0x3200400, 0x3240400,
					0x2000001, 0x2040001, 0x3000001, 0x3040001, 0x2000401, 0x2040401, 0x3000401, 0x3040401, 0x2200001,
					0x2240001, 0x3200001, 0x3240001, 0x2200401, 0x2240401, 0x3200401, 0x3240401 },
			{ 0, 2, 2048, 2050, 0x8000000, 0x8000002, 0x8000800, 0x8000802, 0x10000, 0x10002, 0x10800, 0x10802,
					0x8010000, 0x8010002, 0x8010800, 0x8010802, 256, 258, 2304, 2306, 0x8000100, 0x8000102, 0x8000900,
					0x8000902, 0x10100, 0x10102, 0x10900, 0x10902, 0x8010100, 0x8010102, 0x8010900, 0x8010902, 16, 18,
					2064, 2066, 0x8000010, 0x8000012, 0x8000810, 0x8000812, 0x10010, 0x10012, 0x10810, 0x10812,
					0x8010010, 0x8010012, 0x8010810, 0x8010812, 272, 274, 2320, 2322, 0x8000110, 0x8000112, 0x8000910,
					0x8000912, 0x10110, 0x10112, 0x10910, 0x10912, 0x8010110, 0x8010112, 0x8010910, 0x8010912 },
			{ 0, 4, 4096, 4100, 0x10000000, 0x10000004, 0x10001000, 0x10001004, 32, 36, 4128, 4132, 0x10000020,
					0x10000024, 0x10001020, 0x10001024, 0x80000, 0x80004, 0x81000, 0x81004, 0x10080000, 0x10080004,
					0x10081000, 0x10081004, 0x80020, 0x80024, 0x81020, 0x81024, 0x10080020, 0x10080024, 0x10081020,
					0x10081024, 0x20000000, 0x20000004, 0x20001000, 0x20001004, 0x30000000, 0x30000004, 0x30001000,
					0x30001004, 0x20000020, 0x20000024, 0x20001020, 0x20001024, 0x30000020, 0x30000024, 0x30001020,
					0x30001024, 0x20080000, 0x20080004, 0x20081000, 0x20081004, 0x30080000, 0x30080004, 0x30081000,
					0x30081004, 0x20080020, 0x20080024, 0x20081020, 0x20081024, 0x30080020, 0x30080024, 0x30081020,
					0x30081024 },
			{ 0, 0x100000, 8, 0x100008, 512, 0x100200, 520, 0x100208, 0x4000000, 0x4100000, 0x4000008, 0x4100008,
					0x4000200, 0x4100200, 0x4000208, 0x4100208, 8192, 0x102000, 8200, 0x102008, 8704, 0x102200, 8712,
					0x102208, 0x4002000, 0x4102000, 0x4002008, 0x4102008, 0x4002200, 0x4102200, 0x4002208, 0x4102208,
					0x20000, 0x120000, 0x20008, 0x120008, 0x20200, 0x120200, 0x20208, 0x120208, 0x4020000, 0x4120000,
					0x4020008, 0x4120008, 0x4020200, 0x4120200, 0x4020208, 0x4120208, 0x22000, 0x122000, 0x22008,
					0x122008, 0x22200, 0x122200, 0x22208, 0x122208, 0x4022000, 0x4122000, 0x4022008, 0x4122008,
					0x4022200, 0x4122200, 0x4022208, 0x4122208 },
			{ 0, 512, 0x20000, 0x20200, 1, 513, 0x20001, 0x20201, 0x8000000, 0x8000200, 0x8020000, 0x8020200,
					0x8000001, 0x8000201, 0x8020001, 0x8020201, 0x200000, 0x200200, 0x220000, 0x220200, 0x200001,
					0x200201, 0x220001, 0x220201, 0x8200000, 0x8200200, 0x8220000, 0x8220200, 0x8200001, 0x8200201,
					0x8220001, 0x8220201, 2, 514, 0x20002, 0x20202, 3, 515, 0x20003, 0x20203, 0x8000002, 0x8000202,
					0x8020002, 0x8020202, 0x8000003, 0x8000203, 0x8020003, 0x8020203, 0x200002, 0x200202, 0x220002,
					0x220202, 0x200003, 0x200203, 0x220003, 0x220203, 0x8200002, 0x8200202, 0x8220002, 0x8220202,
					0x8200003, 0x8200203, 0x8220003, 0x8220203 },
			{ 0, 16, 0x20000000, 0x20000010, 0x100000, 0x100010, 0x20100000, 0x20100010, 2048, 2064, 0x20000800,
					0x20000810, 0x100800, 0x100810, 0x20100800, 0x20100810, 0x4000000, 0x4000010, 0x24000000,
					0x24000010, 0x4100000, 0x4100010, 0x24100000, 0x24100010, 0x4000800, 0x4000810, 0x24000800,
					0x24000810, 0x4100800, 0x4100810, 0x24100800, 0x24100810, 4, 20, 0x20000004, 0x20000014, 0x100004,
					0x100014, 0x20100004, 0x20100014, 2052, 2068, 0x20000804, 0x20000814, 0x100804, 0x100814,
					0x20100804, 0x20100814, 0x4000004, 0x4000014, 0x24000004, 0x24000014, 0x4100004, 0x4100014,
					0x24100004, 0x24100014, 0x4000804, 0x4000814, 0x24000804, 0x24000814, 0x4100804, 0x4100814,
					0x24100804, 0x24100814 },
			{ 0, 4096, 0x10000, 0x11000, 0x2000000, 0x2001000, 0x2010000, 0x2011000, 32, 4128, 0x10020, 0x11020,
					0x2000020, 0x2001020, 0x2010020, 0x2011020, 0x40000, 0x41000, 0x50000, 0x51000, 0x2040000,
					0x2041000, 0x2050000, 0x2051000, 0x40020, 0x41020, 0x50020, 0x51020, 0x2040020, 0x2041020,
					0x2050020, 0x2051020, 8192, 12288, 0x12000, 0x13000, 0x2002000, 0x2003000, 0x2012000, 0x2013000,
					8224, 12320, 0x12020, 0x13020, 0x2002020, 0x2003020, 0x2012020, 0x2013020, 0x42000, 0x43000,
					0x52000, 0x53000, 0x2042000, 0x2043000, 0x2052000, 0x2053000, 0x42020, 0x43020, 0x52020, 0x53020,
					0x2042020, 0x2043020, 0x2052020, 0x2053020 },
			{ 0, 1024, 0x1000000, 0x1000400, 256, 1280, 0x1000100, 0x1000500, 0x10000000, 0x10000400, 0x11000000,
					0x11000400, 0x10000100, 0x10000500, 0x11000100, 0x11000500, 0x80000, 0x80400, 0x1080000, 0x1080400,
					0x80100, 0x80500, 0x1080100, 0x1080500, 0x10080000, 0x10080400, 0x11080000, 0x11080400, 0x10080100,
					0x10080500, 0x11080100, 0x11080500, 8, 1032, 0x1000008, 0x1000408, 264, 1288, 0x1000108, 0x1000508,
					0x10000008, 0x10000408, 0x11000008, 0x11000408, 0x10000108, 0x10000508, 0x11000108, 0x11000508,
					0x80008, 0x80408, 0x1080008, 0x1080408, 0x80108, 0x80508, 0x1080108, 0x1080508, 0x10080008,
					0x10080408, 0x11080008, 0x11080408, 0x10080108, 0x10080508, 0x11080108, 0x11080508 } };
	private static final int spbox[][] = {
			{ 0x808200, 0, 32768, 0x808202, 0x808002, 33282, 2, 32768, 512, 0x808200, 0x808202, 512, 0x800202,
					0x808002, 0x800000, 2, 514, 0x800200, 0x800200, 33280, 33280, 0x808000, 0x808000, 0x800202, 32770,
					0x800002, 0x800002, 32770, 0, 514, 33282, 0x800000, 32768, 0x808202, 2, 0x808000, 0x808200,
					0x800000, 0x800000, 512, 0x808002, 32768, 33280, 0x800002, 512, 2, 0x800202, 33282, 0x808202,
					32770, 0x808000, 0x800202, 0x800002, 514, 33282, 0x808200, 514, 0x800200, 0x800200, 0, 32770,
					33280, 0, 0x808002 },
			{ 0x40084010, 0x40004000, 16384, 0x84010, 0x80000, 16, 0x40080010, 0x40004010, 0x40000010, 0x40084010,
					0x40084000, 0x40000000, 0x40004000, 0x80000, 16, 0x40080010, 0x84000, 0x80010, 0x40004010, 0,
					0x40000000, 16384, 0x84010, 0x40080000, 0x80010, 0x40000010, 0, 0x84000, 16400, 0x40084000,
					0x40080000, 16400, 0, 0x84010, 0x40080010, 0x80000, 0x40004010, 0x40080000, 0x40084000, 16384,
					0x40080000, 0x40004000, 16, 0x40084010, 0x84010, 16, 16384, 0x40000000, 16400, 0x40084000, 0x80000,
					0x40000010, 0x80010, 0x40004010, 0x40000010, 0x80010, 0x84000, 0, 0x40004000, 16400, 0x40000000,
					0x40080010, 0x40084010, 0x84000 },
			{ 260, 0x4010100, 0, 0x4010004, 0x4000100, 0, 0x10104, 0x4000100, 0x10004, 0x4000004, 0x4000004, 0x10000,
					0x4010104, 0x10004, 0x4010000, 260, 0x4000000, 4, 0x4010100, 256, 0x10100, 0x4010000, 0x4010004,
					0x10104, 0x4000104, 0x10100, 0x10000, 0x4000104, 4, 0x4010104, 256, 0x4000000, 0x4010100,
					0x4000000, 0x10004, 260, 0x10000, 0x4010100, 0x4000100, 0, 256, 0x10004, 0x4010104, 0x4000100,
					0x4000004, 256, 0, 0x4010004, 0x4000104, 0x10000, 0x4000000, 0x4010104, 4, 0x10104, 0x10100,
					0x4000004, 0x4010000, 0x4000104, 260, 0x4010000, 0x10104, 4, 0x4010004, 0x10100 },
			{ 0x80401000, 0x80001040, 0x80001040, 64, 0x401040, 0x80400040, 0x80400000, 0x80001000, 0, 0x401000,
					0x401000, 0x80401040, 0x80000040, 0, 0x400040, 0x80400000, 0x80000000, 4096, 0x400000, 0x80401000,
					64, 0x400000, 0x80001000, 4160, 0x80400040, 0x80000000, 4160, 0x400040, 4096, 0x401040, 0x80401040,
					0x80000040, 0x400040, 0x80400000, 0x401000, 0x80401040, 0x80000040, 0, 0, 0x401000, 4160, 0x400040,
					0x80400040, 0x80000000, 0x80401000, 0x80001040, 0x80001040, 64, 0x80401040, 0x80000040, 0x80000000,
					4096, 0x80400000, 0x80001000, 0x401040, 0x80400040, 0x80001000, 4160, 0x400000, 0x80401000, 64,
					0x400000, 4096, 0x401040 },
			{ 128, 0x1040080, 0x1040000, 0x21000080, 0x40000, 128, 0x20000000, 0x1040000, 0x20040080, 0x40000,
					0x1000080, 0x20040080, 0x21000080, 0x21040000, 0x40080, 0x20000000, 0x1000000, 0x20040000,
					0x20040000, 0, 0x20000080, 0x21040080, 0x21040080, 0x1000080, 0x21040000, 0x20000080, 0,
					0x21000000, 0x1040080, 0x1000000, 0x21000000, 0x40080, 0x40000, 0x21000080, 128, 0x1000000,
					0x20000000, 0x1040000, 0x21000080, 0x20040080, 0x1000080, 0x20000000, 0x21040000, 0x1040080,
					0x20040080, 128, 0x1000000, 0x21040000, 0x21040080, 0x40080, 0x21000000, 0x21040080, 0x1040000, 0,
					0x20040000, 0x21000000, 0x40080, 0x1000080, 0x20000080, 0x40000, 0, 0x20040000, 0x1040080,
					0x20000080 },
			{ 0x10000008, 0x10200000, 8192, 0x10202008, 0x10200000, 8, 0x10202008, 0x200000, 0x10002000, 0x202008,
					0x200000, 0x10000008, 0x200008, 0x10002000, 0x10000000, 8200, 0, 0x200008, 0x10002008, 8192,
					0x202000, 0x10002008, 8, 0x10200008, 0x10200008, 0, 0x202008, 0x10202000, 8200, 0x202000,
					0x10202000, 0x10000000, 0x10002000, 8, 0x10200008, 0x202000, 0x10202008, 0x200000, 8200,
					0x10000008, 0x200000, 0x10002000, 0x10000000, 8200, 0x10000008, 0x10202008, 0x202000, 0x10200000,
					0x202008, 0x10202000, 0, 0x10200008, 8, 8192, 0x10200000, 0x202008, 8192, 0x200008, 0x10002008, 0,
					0x10202000, 0x10000000, 0x200008, 0x10002008 },
			{ 0x100000, 0x2100001, 0x2000401, 0, 1024, 0x2000401, 0x100401, 0x2100400, 0x2100401, 0x100000, 0,
					0x2000001, 1, 0x2000000, 0x2100001, 1025, 0x2000400, 0x100401, 0x100001, 0x2000400, 0x2000001,
					0x2100000, 0x2100400, 0x100001, 0x2100000, 1024, 1025, 0x2100401, 0x100400, 1, 0x2000000, 0x100400,
					0x2000000, 0x100400, 0x100000, 0x2000401, 0x2000401, 0x2100001, 0x2100001, 1, 0x100001, 0x2000000,
					0x2000400, 0x100000, 0x2100400, 1025, 0x100401, 0x2100400, 1025, 0x2000001, 0x2100401, 0x2100000,
					0x100400, 0, 1, 0x2100401, 0, 0x100401, 0x2100000, 1024, 0x2000001, 0x2000400, 1024, 0x100001 },
			{ 0x8000820, 2048, 0x20000, 0x8020820, 0x8000000, 0x8000820, 32, 0x8000000, 0x20020, 0x8020000, 0x8020820,
					0x20800, 0x8020800, 0x20820, 2048, 32, 0x8020000, 0x8000020, 0x8000800, 2080, 0x20800, 0x20020,
					0x8020020, 0x8020800, 2080, 0, 0, 0x8020020, 0x8000020, 0x8000800, 0x20820, 0x20000, 0x20820,
					0x20000, 0x8020800, 2048, 32, 0x8020020, 2048, 0x20820, 0x8000800, 32, 0x8000020, 0x8020000,
					0x8020020, 0x8000000, 0x20000, 0x8000820, 0, 0x8020820, 0x20020, 0x8000020, 0x8020000, 0x8000800,
					0x8000820, 0, 0x8020820, 0x20800, 0x20800, 2080, 2080, 0x20020, 0x8000000, 0x8020800 } };
}
