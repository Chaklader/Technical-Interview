/***
 * Excerpted from "Functional Programming in Java",
 * published by The Pragmatic Bookshelf.
 * Copyrights apply to this code. It may not be used to create training material, 
 * courses, books, articles, and the like. Contact us if you are in doubt.
 * We make no guarantees that this code is fit for any purpose. 
 * Visit http://www.pragmaticprogrammer.com/titles/vsjava8 for more book information.
***/
package fpij;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import fpij.Asset.AssetType;

public class AssetUtilRefactored {
  public static int totalAssetValues(final List<Asset> assets,
    final Predicate<Asset> assetSelector) {
    return assets.stream()
                 .filter(assetSelector)
                 .mapToInt(Asset::getValue)
                 .sum();
  }
  
  public static void main(final String[] args) {
    List<Asset> assets = Arrays.asList(
      new Asset(Asset.AssetType.BOND, 1000),
      new Asset(Asset.AssetType.BOND, 2000),
      new Asset(Asset.AssetType.STOCK, 3000),
      new Asset(Asset.AssetType.STOCK, 4000)
    );

    System.out.println("Total of all assets: " + 
      totalAssetValues(assets, asset -> true));

    System.out.println("Total of bonds: " + 
      totalAssetValues(assets, asset -> asset.getType() == AssetType.BOND));

    System.out.println("Total of stocks: " + 
      totalAssetValues(assets, asset -> asset.getType() == AssetType.STOCK));
  }
}
