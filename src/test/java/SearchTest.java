/*
 * NCATS-MOLWITCH-CDK
 *
 * Copyright (c) 2020.
 *
 * This work is free software; you can redistribute it and/or modify it under the terms of the
 * GNU Lesser General Public License as published by the Free Software Foundation;
 * either version 2.1 of the License, or (at your option) any later version.
 *
 * This work is distributed in the hope that it will be useful, but without any warranty;
 * without even the implied warranty of merchantability or fitness for a particular purpose.
 * See the GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License along with this library;
 *  if not, write to:
 *
 *  the Free Software Foundation, Inc.
 *  59 Temple Place, Suite 330
 *  Boston, MA 02111-1307 USA
 */

import gov.nih.ncats.molwitch.Chemical;
import gov.nih.ncats.molwitch.search.MolSearcher;
import gov.nih.ncats.molwitch.search.MolSearcherFactory;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.Assert.*;
public class SearchTest {

    @Test
    public void goodMatch() throws Exception{
        String query = "\n   JSDraw209162015292D\n\n" +
                "  6  6  0  0  0  0            999 V2000\n" +
                "   23.8160  -10.3584    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4650   -9.5784    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4650   -8.0184    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.8160   -7.2384    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1670   -8.0184    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1670   -9.5784    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "  1  2  1  0  0  0  0\n" +
                "  2  3  1  0  0  0  0\n" +
                "  3  4  1  0  0  0  0\n" +
                "  4  5  1  0  0  0  0\n" +
                "  5  6  1  0  0  0  0\n" +
                "  6  1  1  0  0  0  0\n" +
                "M  END";

        String target = "\n JSDraw209162015302D\n\n" +
                " 10 11  0  0  0  0            999 V2000\n" +
                "   22.4650   -9.5784    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4650   -8.0184    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.1140   -7.2384    0.0000 N   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.7630   -8.0184    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.7630   -9.5784    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.1140  -10.3584    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   28.7045   -9.7760    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   28.7045   -6.6560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   30.0555   -7.4360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   30.0555   -8.9960    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "  1  2  1  0  0  0  0\n" +
                "  2  3  1  0  0  0  0\n" +
                "  3  4  1  0  0  0  0\n" +
                "  4  5  1  0  0  0  0\n" +
                "  5  6  1  0  0  0  0\n" +
                "  6  1  1  0  0  0  0\n" +
                "  8  9  1  0  0  0  0\n" +
                "  9 10  1  0  0  0  0\n" +
                " 10  7  1  0  0  0  0\n" +
                "  2  8  1  0  0  0  0\n" +
                "  7  1  1  0  0  0  0\n" +
                "M  END";

        Chemical queryChem = Chemical.parseMol(query);
        Chemical targetChem = Chemical.parseMol(target);

        MolSearcher searcher = MolSearcherFactory.create(queryChem).get();

        Optional<int[]> result = searcher.search(targetChem);

        Assert.assertArrayEquals( new int[]{0, 1, 7, 8, 9, 6},result.get());
    }

    @Test
    public void notSubgraphShouldReturnEmptyOptional() throws Exception{
        String query = "\n   JSDraw209162015292D\n\n" +
                "  6  6  0  0  0  0            999 V2000\n" +
                "   23.8160  -10.3584    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4650   -9.5784    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4650   -8.0184    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.8160   -7.2384    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1670   -8.0184    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1670   -9.5784    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "  1  2  1  0  0  0  0\n" +
                "  2  3  1  0  0  0  0\n" +
                "  3  4  1  0  0  0  0\n" +
                "  4  5  1  0  0  0  0\n" +
                "  5  6  1  0  0  0  0\n" +
                "  6  1  1  0  0  0  0\n" +
                "M  END";

        String target = "\n Symyx   08281518122D 1   1.00000     0.00000     0\n\n" +
                " 27 29  0  0  1  0            999 V2000\n" +
                "    7.1375   -2.2042    0.0000 N   0  0  3  0  0  0           0  0  0\n" +
                "    6.0500   -1.8583    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    6.0500   -0.7125    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    7.1292   -0.3542    0.0000 N   0  0  0  0  0  0           0  0  0\n" +
                "    7.8083   -1.2792    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    5.0042   -0.0375    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    5.0042   -2.4667    0.0000 N   0  0  0  0  0  0           0  0  0\n" +
                "    3.9333   -0.6458    0.0000 N   0  0  0  0  0  0           0  0  0\n" +
                "    3.9333   -1.8583    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    5.0042    1.1417    0.0000 N   0  0  0  0  0  0           0  0  0\n" +
                "    7.1042   -3.9750    0.0000 C   0  0  1  0  0  0           0  0  0\n" +
                "    6.7583   -5.0833    0.0000 C   0  0  1  0  0  0           0  0  0\n" +
                "    6.1333   -3.2625    0.0000 O   0  0  0  0  0  0           0  0  0\n" +
                "    5.1750   -3.9625    0.0000 C   0  0  1  0  0  0           0  0  0\n" +
                "    5.5583   -5.0833    0.0000 C   0  0  1  0  0  0           0  0  0\n" +
                "    4.1500   -3.3708    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    3.1250   -3.9625    0.0000 S   0  3  2  0  0  0           0  0  0\n" +
                "    2.1000   -3.3708    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    1.0750   -3.9625    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    0.0500   -3.3708    0.0000 C   0  0  1  0  0  0           0  0  0\n" +
                "   -0.9750   -3.9625    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "   -2.0000   -3.3708    0.0000 O   0  5  0  0  0  0           0  0  0\n" +
                "    5.2458   -6.2250    0.0000 O   0  0  0  0  0  0           0  0  0\n" +
                "    7.3458   -6.1083    0.0000 O   0  0  0  0  0  0           0  0  0\n" +
                "    3.1208   -5.1417    0.0000 C   0  0  0  0  0  0           0  0  0\n" +
                "    0.0458   -2.1917    0.0000 N   0  0  0  0  0  0           0  0  0\n" +
                "   -0.9792   -5.1417    0.0000 O   0  0  0  0  0  0           0  0  0\n" +
                " 11  1  1  1     0  0\n" +
                " 12 11  1  0     0  0\n" +
                " 13 11  1  0     0  0\n" +
                " 14 13  1  0     0  0\n" +
                " 15 12  1  0     0  0\n" +
                " 15 14  1  0     0  0\n" +
                "  6  3  1  0     0  0\n" +
                " 14 16  1  1     0  0\n" +
                "  7  2  1  0     0  0\n" +
                " 16 17  1  0     0  0\n" +
                "  8  9  1  0     0  0\n" +
                " 17 18  1  0     0  0\n" +
                "  9  7  2  0     0  0\n" +
                " 18 19  1  0     0  0\n" +
                " 10  6  1  0     0  0\n" +
                " 19 20  1  0     0  0\n" +
                "  3  4  1  0     0  0\n" +
                " 20 21  1  0     0  0\n" +
                "  8  6  2  0     0  0\n" +
                " 21 22  1  0     0  0\n" +
                " 15 23  1  6     0  0\n" +
                "  2  1  1  0     0  0\n" +
                " 12 24  1  6     0  0\n" +
                "  3  2  2  0     0  0\n" +
                " 17 25  1  1     0  0\n" +
                "  4  5  2  0     0  0\n" +
                " 20 26  1  1     0  0\n" +
                "  5  1  1  0     0  0\n" +
                " 21 27  2  0     0  0\n" +
                "M  CHG  2  17   1  22  -1\n" +
                "M  END";


        Chemical queryChem = Chemical.parseMol(query);
        Chemical targetChem = Chemical.parseMol(target);

        MolSearcher searcher = MolSearcherFactory.create(queryChem).get();

        Optional<int[]> result = searcher.search(targetChem);

        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void largeTarget() throws Exception{
        String query = "\n   JSDraw209162015292D\n\n" +
                "  6  6  0  0  0  0            999 V2000\n" +
                "   23.8160  -10.3584    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4650   -9.5784    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4650   -8.0184    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.8160   -7.2384    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1670   -8.0184    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1670   -9.5784    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "  1  2  1  0  0  0  0\n" +
                "  2  3  1  0  0  0  0\n" +
                "  3  4  1  0  0  0  0\n" +
                "  4  5  1  0  0  0  0\n" +
                "  5  6  1  0  0  0  0\n" +
                "  6  1  1  0  0  0  0\n" +
                "M  END";

        String target = "\n   JSDraw209162015532D\n\n" +
                " 41 53  0  0  0  0            999 V2000\n" +
                "   16.5360   -5.3560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.1850   -4.5760    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.1850   -3.0160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   16.5360   -2.2360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   17.8870   -3.0160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   17.8870   -4.5760    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.2380   -5.3560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   20.5890   -4.5760    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   20.5890   -3.0160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.2380   -2.2360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.9400   -2.2360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.2910   -3.0160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.2910   -4.5760    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.9400   -5.3560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   24.6420   -5.3560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.9930   -4.5760    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.9930   -3.0160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   24.6420   -2.2360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   27.3440   -2.2360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   28.6950   -3.0160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   28.6950   -4.5760    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   27.3440   -5.3560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   16.5360   -6.9160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   17.8870   -7.6960    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.2380   -6.9160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.9400   -6.9160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   20.5890   -7.6960    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.2910   -7.6960    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   24.6420   -6.9160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   27.3440   -6.9160    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.9930   -7.6960    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   20.5890   -9.2560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.2380  -10.0360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   17.8870   -9.2560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.9400  -10.0360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.2910   -9.2560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.9930   -9.2560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   24.6420  -10.0360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.1850   -7.6960    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.1850   -9.2560    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   16.5360  -10.0360    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "  1  2  1  0  0  0  0\n" +
                "  2  3  1  0  0  0  0\n" +
                "  3  4  1  0  0  0  0\n" +
                "  4  5  1  0  0  0  0\n" +
                "  5  6  1  0  0  0  0\n" +
                "  6  1  1  0  0  0  0\n" +
                "  6  7  1  0  0  0  0\n" +
                "  7  8  1  0  0  0  0\n" +
                "  8  9  1  0  0  0  0\n" +
                "  9 10  1  0  0  0  0\n" +
                " 10  5  1  0  0  0  0\n" +
                "  9 11  1  0  0  0  0\n" +
                " 11 12  1  0  0  0  0\n" +
                " 12 13  1  0  0  0  0\n" +
                " 13 14  1  0  0  0  0\n" +
                " 14  8  1  0  0  0  0\n" +
                " 13 15  1  0  0  0  0\n" +
                " 15 16  1  0  0  0  0\n" +
                " 16 17  1  0  0  0  0\n" +
                " 17 18  1  0  0  0  0\n" +
                " 18 12  1  0  0  0  0\n" +
                " 17 19  1  0  0  0  0\n" +
                " 19 20  1  0  0  0  0\n" +
                " 20 21  1  0  0  0  0\n" +
                " 21 22  1  0  0  0  0\n" +
                " 22 16  1  0  0  0  0\n" +
                "  1 23  1  0  0  0  0\n" +
                " 23 24  1  0  0  0  0\n" +
                " 24 25  1  0  0  0  0\n" +
                " 25  7  1  0  0  0  0\n" +
                " 14 26  1  0  0  0  0\n" +
                " 26 27  1  0  0  0  0\n" +
                " 27 25  1  0  0  0  0\n" +
                " 26 28  1  0  0  0  0\n" +
                " 28 29  1  0  0  0  0\n" +
                " 29 15  1  0  0  0  0\n" +
                " 22 30  1  0  0  0  0\n" +
                " 30 31  1  0  0  0  0\n" +
                " 31 29  1  0  0  0  0\n" +
                " 27 32  1  0  0  0  0\n" +
                " 32 33  1  0  0  0  0\n" +
                " 33 34  1  0  0  0  0\n" +
                " 34 24  1  0  0  0  0\n" +
                " 32 35  1  0  0  0  0\n" +
                " 35 36  1  0  0  0  0\n" +
                " 36 28  1  0  0  0  0\n" +
                " 31 37  1  0  0  0  0\n" +
                " 37 38  1  0  0  0  0\n" +
                " 38 36  1  0  0  0  0\n" +
                " 23 39  1  0  0  0  0\n" +
                " 39 40  1  0  0  0  0\n" +
                " 40 41  1  0  0  0  0\n" +
                " 41 34  1  0  0  0  0\n" +
                "M  END";

        Chemical queryChem = Chemical.parseMol(query);
        Chemical targetChem = Chemical.parseMol(target);

        MolSearcher searcher = MolSearcherFactory.create(queryChem).get();

        Optional<int[]> result = searcher.search(targetChem);
        Assert.assertArrayEquals( new int[]{0,1,2,3,4,5},result.get());
    }

    @Test
    public void singleAtomAgainstItself() throws Exception{
        Chemical p=Chemical.parseMol("\n" +
                "   JSDraw209162010482D\n" +
                "\n" +
                "  1  0  0  0  0  0            999 V2000\n" +
                "   15.8080   -7.0436    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "M  END");
        Chemical t=Chemical.parseMol("\n" +
                "   JSDraw209162010482D\n" +
                "\n" +
                "  1  0  0  0  0  0            999 V2000\n" +
                "   15.8080   -7.0436    0.0000 O   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "M  END");
        Optional<int[]> hit = MolSearcherFactory.create(p).get().search(t);
        assertTrue("should have hit" , hit.isPresent());
        Assert.assertArrayEquals( new int[]{0}, hit.get()) ;
    }

    @Test
    public void ensureIsobutaneSSSDoesntReturnIsoPentene() throws Exception{


        Chemical p=Chemical.parseMol("\n" +
                "   JSDraw209182020002D\n" +
                "\n" +
                "  4  3  0  0  0  0              0 V2000\n" +
                "   23.1921   -7.4013    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.6531   -8.8915    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1741   -9.2375    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.5929  -10.0359    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "  1  2  1  0  0  0  0\n" +
                "  2  3  1  0  0  0  0\n" +
                "  2  4  1  0  0  0  0\n" +
                "M  END");

        Chemical t=Chemical.parseMol("\n" +
                "   JSDraw209182020002D\n" +
                "\n" +
                "  5  4  0  0  0  0              0 V2000\n" +
                "   23.1921   -7.4013    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.6531   -8.8915    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1741   -9.2375    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.5929  -10.0359    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   26.2344   -8.0932    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "  1  2  2  0  0  0  0\n" +
                "  2  3  1  0  0  0  0\n" +
                "  2  4  1  0  0  0  0\n" +
                "  3  5  1  0  0  0  0\n" +
                "M  END");



        Optional<int[]> hit = MolSearcherFactory.create(p).get().search(t);

        assertFalse(hit.isPresent());
    }

    @Test
    public void hugeNumberOfResultsShouldntCrash() throws IOException {
        String target = "\n" +
                "   JSDraw209212013062D\n\n" +
                " 98133  0  0  0  0              0 V2000\n" +
                "   14.4560   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   13.1050   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   13.1050   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   14.4560   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.8070   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.8070   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   17.1580   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   18.5090   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   18.5090   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   17.1580   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.8600   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.2110   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.2110   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.8600   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.5620   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.9130   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.9130   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.5620   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.2640   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   26.6150   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   26.6150   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.2640   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   27.9660   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   29.3170   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   29.3170   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   27.9660   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   30.6680   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   32.0190   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   32.0190   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   30.6680   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   33.3700   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   34.7210   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   34.7210   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   33.3700   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   36.0720   -4.5968    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   37.4230   -5.3768    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   37.4230   -6.9368    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   36.0720   -7.7168    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   13.0080  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   11.6570   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   11.6570   -7.8000    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   14.3590   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.7100  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   17.0610   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.7630   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   18.4120  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.1140  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4650   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1670   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.8160  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   26.5180  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   27.8690   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   30.5710   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   29.2200  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   31.9220  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   33.2730   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   35.9750   -9.3600    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   34.6240  -10.1400    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   11.6520  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   10.3010  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   10.3010  -10.3325    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   13.0030  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   14.3540  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.7050  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   18.4070  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   17.0560  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.7580  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.1090  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.8110  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.4600  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.1620  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   26.5130  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   29.2150  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   27.8640  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   30.5660  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   31.9170  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   34.6190  -11.8925    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   33.2680  -12.6725    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   10.2040  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "    8.8530  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "    8.8530  -12.7555    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   11.5550  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   12.9060  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   14.2570  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   16.9590  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   15.6080  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   18.3100  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   19.6610  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   22.3630  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   21.0120  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   23.7140  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   25.0650  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   27.7670  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   26.4160  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   29.1180  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   30.4690  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   33.1710  -14.3155    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "   31.8200  -15.0955    0.0000 C   0  0  0  0  0  0  0  0  0  0  0  0\n" +
                "  1  2  1  0  0  0  0\n" +
                "  2  3  1  0  0  0  0\n" +
                "  3  4  1  0  0  0  0\n" +
                "  4  5  1  0  0  0  0\n" +
                "  5  6  1  0  0  0  0\n" +
                "  6  1  1  0  0  0  0\n" +
                "  6  7  1  0  0  0  0\n" +
                "  7  8  1  0  0  0  0\n" +
                "  8  9  1  0  0  0  0\n" +
                "  9 10  1  0  0  0  0\n" +
                " 10  5  1  0  0  0  0\n" +
                "  9 11  1  0  0  0  0\n" +
                " 11 12  1  0  0  0  0\n" +
                " 12 13  1  0  0  0  0\n" +
                " 13 14  1  0  0  0  0\n" +
                " 14  8  1  0  0  0  0\n" +
                " 13 15  1  0  0  0  0\n" +
                " 15 16  1  0  0  0  0\n" +
                " 16 17  1  0  0  0  0\n" +
                " 17 18  1  0  0  0  0\n" +
                " 18 12  1  0  0  0  0\n" +
                " 17 19  1  0  0  0  0\n" +
                " 19 20  1  0  0  0  0\n" +
                " 20 21  1  0  0  0  0\n" +
                " 21 22  1  0  0  0  0\n" +
                " 22 16  1  0  0  0  0\n" +
                " 21 23  1  0  0  0  0\n" +
                " 23 24  1  0  0  0  0\n" +
                " 24 25  1  0  0  0  0\n" +
                " 25 26  1  0  0  0  0\n" +
                " 26 20  1  0  0  0  0\n" +
                " 25 27  1  0  0  0  0\n" +
                " 27 28  1  0  0  0  0\n" +
                " 28 29  1  0  0  0  0\n" +
                " 29 30  1  0  0  0  0\n" +
                " 30 24  1  0  0  0  0\n" +
                " 29 31  1  0  0  0  0\n" +
                " 31 32  1  0  0  0  0\n" +
                " 32 33  1  0  0  0  0\n" +
                " 33 34  1  0  0  0  0\n" +
                " 34 28  1  0  0  0  0\n" +
                " 33 35  1  0  0  0  0\n" +
                " 35 36  1  0  0  0  0\n" +
                " 36 37  1  0  0  0  0\n" +
                " 37 38  1  0  0  0  0\n" +
                " 38 32  1  0  0  0  0\n" +
                " 39 40  1  0  0  0  0\n" +
                " 40 41  1  0  0  0  0\n" +
                " 41  2  1  0  0  0  0\n" +
                "  1 42  1  0  0  0  0\n" +
                " 42 39  1  0  0  0  0\n" +
                " 42 43  1  0  0  0  0\n" +
                " 43 44  1  0  0  0  0\n" +
                " 44  7  1  0  0  0  0\n" +
                " 14 45  1  0  0  0  0\n" +
                " 45 46  1  0  0  0  0\n" +
                " 46 44  1  0  0  0  0\n" +
                " 45 47  1  0  0  0  0\n" +
                " 47 48  1  0  0  0  0\n" +
                " 48 15  1  0  0  0  0\n" +
                " 22 49  1  0  0  0  0\n" +
                " 49 50  1  0  0  0  0\n" +
                " 50 48  1  0  0  0  0\n" +
                " 49 51  1  0  0  0  0\n" +
                " 51 52  1  0  0  0  0\n" +
                " 52 23  1  0  0  0  0\n" +
                " 30 53  1  0  0  0  0\n" +
                " 53 54  1  0  0  0  0\n" +
                " 54 52  1  0  0  0  0\n" +
                " 53 55  1  0  0  0  0\n" +
                " 55 56  1  0  0  0  0\n" +
                " 56 31  1  0  0  0  0\n" +
                " 38 57  1  0  0  0  0\n" +
                " 57 58  1  0  0  0  0\n" +
                " 58 56  1  0  0  0  0\n" +
                " 59 60  1  0  0  0  0\n" +
                " 60 61  1  0  0  0  0\n" +
                " 61 40  1  0  0  0  0\n" +
                " 39 62  1  0  0  0  0\n" +
                " 62 59  1  0  0  0  0\n" +
                " 62 63  1  0  0  0  0\n" +
                " 63 64  1  0  0  0  0\n" +
                " 64 43  1  0  0  0  0\n" +
                " 46 65  1  0  0  0  0\n" +
                " 65 66  1  0  0  0  0\n" +
                " 66 64  1  0  0  0  0\n" +
                " 65 67  1  0  0  0  0\n" +
                " 67 68  1  0  0  0  0\n" +
                " 68 47  1  0  0  0  0\n" +
                " 50 69  1  0  0  0  0\n" +
                " 69 70  1  0  0  0  0\n" +
                " 70 68  1  0  0  0  0\n" +
                " 69 71  1  0  0  0  0\n" +
                " 71 72  1  0  0  0  0\n" +
                " 72 51  1  0  0  0  0\n" +
                " 54 73  1  0  0  0  0\n" +
                " 73 74  1  0  0  0  0\n" +
                " 74 72  1  0  0  0  0\n" +
                " 73 75  1  0  0  0  0\n" +
                " 75 76  1  0  0  0  0\n" +
                " 76 55  1  0  0  0  0\n" +
                " 58 77  1  0  0  0  0\n" +
                " 77 78  1  0  0  0  0\n" +
                " 78 76  1  0  0  0  0\n" +
                " 79 80  1  0  0  0  0\n" +
                " 80 81  1  0  0  0  0\n" +
                " 81 60  1  0  0  0  0\n" +
                " 59 82  1  0  0  0  0\n" +
                " 82 79  1  0  0  0  0\n" +
                " 82 83  1  0  0  0  0\n" +
                " 83 84  1  0  0  0  0\n" +
                " 84 63  1  0  0  0  0\n" +
                " 66 85  1  0  0  0  0\n" +
                " 85 86  1  0  0  0  0\n" +
                " 86 84  1  0  0  0  0\n" +
                " 85 87  1  0  0  0  0\n" +
                " 87 88  1  0  0  0  0\n" +
                " 88 67  1  0  0  0  0\n" +
                " 70 89  1  0  0  0  0\n" +
                " 89 90  1  0  0  0  0\n" +
                " 90 88  1  0  0  0  0\n" +
                " 89 91  1  0  0  0  0\n" +
                " 91 92  1  0  0  0  0\n" +
                " 92 71  1  0  0  0  0\n" +
                " 74 93  1  0  0  0  0\n" +
                " 93 94  1  0  0  0  0\n" +
                " 94 92  1  0  0  0  0\n" +
                " 93 95  1  0  0  0  0\n" +
                " 95 96  1  0  0  0  0\n" +
                " 96 75  1  0  0  0  0\n" +
                " 78 97  1  0  0  0  0\n" +
                " 97 98  1  0  0  0  0\n" +
                " 98 96  1  0  0  0  0\n" +
                "M  END";

        Chemical query = Chemical.createFromSmiles("CCCCCCCCCCCCCCCC.CCCCCCCCCCCCCCCCCCC");

        Optional<int[]> hit = MolSearcherFactory.create(query).get().search(Chemical.parseMol(target));

        assertTrue(hit.isPresent());
    }
}
