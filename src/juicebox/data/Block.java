/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2011-2020 Broad Institute, Aiden Lab, Rice University, Baylor College of Medicine
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package juicebox.data;

//import java.awt.*;
//import java.util.List;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * @author jrobinso
 * @since Aug 10, 2010
 */
public class Block {

    private final int number;
    private final String uniqueRegionID;
    private final List<ContactRecord> records;

    public Block(int number, String regionID) {
        this.number = number;
        records = new ArrayList<>();
        uniqueRegionID = regionID + "_" + number;
    }

    public Block(int number, List<ContactRecord> records, String regionID) {
        this.number = number;
        this.records = records;
        this.uniqueRegionID = regionID + "_" + number;
    }

    public int getNumber() {
        return number;
    }

    public String getUniqueRegionID() {
        return uniqueRegionID;
    }

    public List<ContactRecord> getContactRecords() {
        return records;
    }

    public List<ContactRecord> getContactRecords(double subsampleFraction, Random randomSubsampleGenerator) {
        List<ContactRecord> newRecords = new ArrayList<>();
        for (ContactRecord i : records) {
            int newBinX = i.getBinX();
            int newBinY = i.getBinY();
            int newCounts = 0;
            for (int j = 0; j < (int) i.getCounts(); j++) {
                if ( subsampleFraction <= 1 && subsampleFraction > 0 && randomSubsampleGenerator.nextDouble() <= subsampleFraction) {
                    newCounts += 1;
                }
            }
            newRecords.add(new ContactRecord(newBinX, newBinY, (float) newCounts));
        }
        return newRecords;
    }

    public void clear() {
        records.clear();
    }
}
