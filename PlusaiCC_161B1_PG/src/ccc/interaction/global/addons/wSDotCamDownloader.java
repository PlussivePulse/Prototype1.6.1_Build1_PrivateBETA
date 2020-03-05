package ccc.interaction.global.addons;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import ccc.interaction.internalFeatures.Base64EncoderStatic;
import ccc.mainComponent.UI.mainRunnerCore;

public class wSDotCamDownloader {
	
	private static ArrayList<ImageIcon> imageIconTemp = new ArrayList<ImageIcon>();
	public static ArrayList<ImageIcon> imageIcon = new ArrayList<ImageIcon>();
	public static String[] ellensburg = {"https://images.wsdot.wa.gov/sc/090vc11547.jpg"
			,"https://images.wsdot.wa.gov/rweather/UMRidge_medium.jpg"
			,"https://images.wsdot.wa.gov/sc/090VC12585.jpg"
			,"https://images.wsdot.wa.gov/sc/090VC10753.jpg"
			,"https://images.wsdot.wa.gov/sc/090VC10107.jpg"};
	public static String[] selection = ellensburg;
	private static String offline = "iVBORw0KGgoAAAANSUhEUgAAAMgAAADICAYAAACtWK6eAAAABHNCSVQICAgIfAhkiAAAAAlwSFlzAAALEwAACxMBAJqcGAAADmJJREFUeJztnXuwp3Mdx1/nnGVdll2cLZfkfomwcinKLZIQRZgaM3RRjUpUJDX5uSdEIboxqKg0qVFtF6Ey5JpQu0Ryl9uydu1i9/THZ89Yx7n8zvf7+X6/z+X9mvnMmFnn+b4/z/O8n9/zfC+fLwghhBBCCCGEEEIIIYQQQgghhBBCCCGEEEIIIYQQQgghhBBCCCGEEEIIIYQQQgghhBBCCCGEEO2ip1C7SwB7A7sBawILgBnAL4BrCmkSohJsD9wLDIwQfwHWLqZOiILsAbzIyOYYjFnAvoU0ClGEVbAbfyxzDMZLwD5FlApRgDPo3hyD8SLwvhJihcjNQ4zfIIMm2buAXiGysQJh5ljcJHtlVy1EJl5PnEEGgPnAnrmFC5GDXuB5fEyyR2btosXkHCi8HJ+u2/nA+4HfOhyrNL3AqsAbganAZGDion+bj/X6PQE8ADyCPSREQ9kCGzGP/RUZAOYB784r34WpwAeBc4G/AXPoPufngRuAs4H9gRUzaxcZOAofgwwALwC75pUfRD/wWeA6/B4QA8DLwLXAoVgniGgIHXxN8q6s6rtnS+DH2KuSV76jnYeLgE2zZCaS08Hv5pgL7JJV/ei8Bfs+Sm2KkeIK4M3JsxTJ6eBrkndmVf9a+oELgYWUM8dgvAycB0xJmrFITge/m2IOsFNW9a+wH9bbVNoYQ+NRNHZUezr4mmSHjNqXAi5w1J8qzsbW4Iia0sHvZngeW3OSmtWAWxx1p47rsG5mUVM6+Jpku4RaNwIedNSbK+4F1klwPkQmOvjdDLOBdyTQuAnV/N7oNh4GNnQ/KyIbHXxN8nZHbT3AXY76SppkLcfzIjLTwe9meA7YxknXeo66SsdMNF2l1nTwuxmeBd7moGl1R01ViD8AfQ7npbFU+eRcg73S7OhwrInYBL+rsdeLUJ4DtsX/Q/cR4FbgRuAm4E5sBu88bLBvgnN7g6yNzSi+OtHxRQY6+D0xZwFbR+pZCfh7pI65wE+wmb0rj9HeBGAaNuHxKnwnPA4sOl7KHj+RgQ6+JtkqUs9KwO0BbT8GHEncFJA1gNPxWYA2GHdjg56ixnTwuyGewdanxNBP9yZ5ETgZWDayzcVZGZv75XVOjnXUJgrRwe+GeBqbeRtDP/CPMdqZCWwe2c5o7AY8PoaGbmIOtrpR1JwOviaJvXlHM8l0YPnI43fD6sR/Fw1gKx1FA+jgZ5KnsI/gGKYCtw057qXknSA4GbieuHMxD6tAIxpABz+TPAlsFqlnSaxX6hjKLeCaAtxB3Lno5BYt0tHB1yRNWLK6FvbqGHoeHqTa42NinHTwM8kT2GTEunMAceehSkuYhQMd/EzyP5phkl8Rfg6+U0CvSEwHmWRx1sfWpofk/1ABvSIDHWSSxbmM8Pw3KqBXZKCDTDLI9oTnfkgBvSITHWQSsNnQDxCW9/kF9IqMdJBJwD64Q3L+awmxIi8dZJKDCcv30QJaRQE6tNskmxOe75IF9IqMrAf8Gj+D1NEkyxOe6yoF9IoM9AFfxCbfeZqjriYJPQ/rlxAr0rIq8GfSGKOuJnmGsBzrkp/okm3xWTzUNJPMIiy/jUuIFWnYB9tIJpc56mKSXsKnnKxRQK9IwIGE3wRNN8kbCM/Lcw29KMQH8C+F0yST7EpYPrNKiBW+7ECePQDrbJKvEJbLfcAGqDxpbVmTalZZr5pJriY+p+exwhSXAUdjO3ktnTMJMT6WwMp0ljZD1U0yFXiJNDnOA/4IHIZtJiQqxEn4X/DnsNKeTTLJ58nzQFiAmeUAtNVbcTbHt8dqBvAxXnllOM7x2CVNMgG4fwx9KeIhrLyqesAK0IPfq9VTwMcZvoJHE0xySKBez7wPQ78oWfkQPhdvOmNXVT/eqa0SJpmMFcouaZDBmEH5PexbQR+2CWXsBTsJ+yXqhhMc2ithkgsddXvFd4HlUibddjx+PQ4PaPdEh3ZzmuRAR73ecQ/xVfXFCNxE3MX5akTbnr1mKU2yLWXmo40nXsBWOQpHphF3UX7moKHqJplG+LT2EnEK3b/qijE4g/AL8SBxOzotzskROlKb5FpHbbniB9hsYxFJaOmaAWwavCenRGhJaZKYc1QyLkYmiWITwk9+qtI1X4vQlMokFzhqyh3nOeTfWo4g/MTvnlDXqRG6UphkRWz76JD2fw78Hpv8WWpdzZcj828tPyXshN9P+o/AqplkZWxPxPG0O5PXzsztw7aW2xRbb3MiNis45dKChcD7IvNvJaGDg6dm0vf1QH2pTLIa3Z+z+cCW4zj2JGwXrenYDe1tklnA2iFJt5WlCL8Q22fUeVqgxlQmWYPuPto/GdHG+sD38Z9OfwPa4apr3k7YSX6J/At6Tg/Umsok6wKPjNLGGZHHH2QD4HejtBMS+h4ZhS2wi/cvwk/wXdlVG1UzyZsWHWfosS/G//vsYGxNjUfu87CqmGIRE4FPMPJ+4+ONX+eV/ypiBjVTmGRTXj2z9zLSvcKsjz2c6n4NK8ME4DOM/ioQEpfkTGIYvoFfLh4mmYL1Rm0XeZxumAz8CZ/cd86gt7LshN/TZmhUYeDpTKplkpwshc93yfW5hVeBpYGzSdNNOBhHZMtmdNpskmWwGzw271YttlqH8NHe8cSZuRLqgrNor0mmEj9H7MrsqguxA/Ak6c1RNYNAu02yDXFjJQuAtbKrzsx7yLuQJ2YALBXfpL0miV1Lc2x+yfnYnbzlQV/GCjZXkW/RTpNMxEqZhuY6M7/kPGyBla3MZY4BbFfXKnM27TTJAcTlulF+yWlZBf/xjbHiDupROeMc2meSHuI6aL6QX3I6evEbLOo2rsJ6TepCG03yEcJznF5AbzK+RHpDLMR6xa4E9qWeBQDOpV0mWQZ4lrD8ZtGQpbnr4N9jdSe27mIvbBLbcjTjZPUA36ZdJrmY8Pw2LKDXnSvxudgLsIl1TS801oNNjWmLSfYnPLePF9DrSuj6jaFxM/CWzNpL0iaTTCUut39h1eNXyC3cA4/9Nc6inRXBe4DzaYdJ/k18frOxOmWTM2sPJqY8z2CE1M5tEj3YOE7TTfJL/HJ8DHttqzyxPTJH55dcSXqwSuhNNonn3LTBuBCbZl9JJgBPE57cj/NLrjRNN0mqYYAbsHJFlWMXwpN6GFg+v+TK0wN8j2aa5DOkMcgAtgivcgPGMTNVDyygty401SSpt4e7GRuUrAy3EpbI3TRjwC8lPVj9qSaZZDfSGmQAG0OrBMsQviDmcwX01pEebKuApphkOWDuMLq84+BM+YzKloQnsGoBvXWlF98K7qVN4rk2ZqR4mgp8j4ROHShV3K3ONMkkU4D/dqExNs7JldBIHEWY8ItKiG0AvfjuYlvSJBuT3iTzsLVJQXh8IK8Y+Hf3OLTdRhYCH8XvATMVmyJUwiR3AZthJVufStTGRApPcgx9l/x0CbENohczSRN+ScDyWQfYGtgPq0xzPz653ZcvjdcSOneoipVH6kbTTDKUXqxk6n+Iz22zUAGxzAn8u0kObbedhcCHsUVIHpR83RqOhcDlmJ4rIo+1U8gfeRhkduDfvdGhbfGKSbwKdlfNJGCVcT5AXMX3rZ20jJvDCfvJu6qE2AbTi5mkqa9bYB1Cw+130k3cVkAvAHt2KXBozAWWLKC3yfQCP6TZJvkCYbmk6iUbk/W6FDhcvLeA3qbTR7NN8nrCdgZ4uYRYsAsypwuBw0Xsh5cYnj7gRzTXJKEV44ttDvr7LgUOjYXYaKrwpw9biNZEk9xM2L1WjKNHENVNNKpiXsVoqklCdiZ7oYjSRWw2gqhu46D8kltDH3ApzTFJH2EF0R8vIXZxYnaonQNMyy+5NfRhC4iaYJLtutQ4NIp18w4S2v02GI+ivbFT0hSThI71/KyA1lfRT3hv1uIn/a25hbeIPuAn1NckW2LdtSFaj8moc0Q8dnWdD3yegl1yDWcC8FPqZ5LXAfdG6Nwxg8YxWRW/yu63Ydu3CX8mYK8cdTHJelhN3lB9z1KhcrbH4nfiB7CtD45C4yXe1MEkKwFfJf7VPXi2c4pNZyZiN/W6CY79FDAD+6CfjSUPlkdPF/9dhf+3SnqWwPfB8wSwM7YVXiwHYuWOJjoca0fgWofjuLED4R9TinqH1y/JTU56orp3U30IDy7ED1qkImrNstjajemYWUJZHtjVQc+h2FtH5egFfkP5J5qiTHj8knQiNVTqtWo4JgE3Uv5iKcqEh0mOC2z7BWqyp2E/cV10inpHKZN8KrLNrPSjX5I2h4dJjh9He7UsSjgJfZO0OXKZ5LfUeCl3D/Bl1AXc1vAwyQmjHP9X+IybFGd7YCblL5gif6QyyRk0bP7eksBXyLNPhKJa4WGSQ4DbseW3e0Ueq9KsDJzGK1NHFO2I0isTa8eKWBG60O3cFPWLypskxWRFD96E7WO3M/a9slxZOSIhnhMc3amqQRanB6vjuwG2LmAKNk9n0qJ/G+np1G1xsfEUIavT/5uy/SWw3XeDKqYPwxPYvD3tOiYaw0rYR7LX69bj2JuDEI3B2ySPYm8KQjQGb5M8jKrbiIbhbZKHsO3YhGgM3iZ5AFgrawZCJMbbJPcDa+RMQIjU9ONrkvuA1bNmIERivE3yb2C1rBkIkRhvk9wNrJI1AyES422SGdjkViEag7dJ/onV6RWiMfQTt3/M0LgT289diMbgbZLbsW5lIRqDt0luAZbOmoEQifE2yWl55QuRHk+TzMXWBwnRKDxNsmcqkb2pDizEGDyJ31LbZAOIMogoidd69AUOWoSoLFOJe93aKr9kIfISapL7qEfxESGiCTHJQUWUClGI8ZjkkkIahSjKFOByRjbGbOBIMrxa6d1NVJmtgAOAaVh1zUeAa4AfYluCCyGEEEIIIYQQQgghhBBCCCGEEEIIIYQQQgghhBBCCCGEEEIIIYQQxfg/12q2+PoOyhcAAAAASUVORK5CYII=";
	
	
	///////////////////////////////////////////////////////////////
	
	private static void addImage(ImageIcon image) {
		imageIconTemp.add(image);
		
	}
	
	private static ImageIcon transfer(Image image) {
		ImageIcon converted = new ImageIcon(image);
		if(converted==null) {
			//alert error
		}
		return converted;
	}
	
	private void submitIcon(byte[] image) {
		Image con = null;
		try {
			BufferedImage bImage = ImageIO.read(new ByteArrayInputStream(image));
			 con = bImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		addImage(transfer(con));
	}
	
	////////////////////////////////////////////////////////////////
	
	public static void loadImage() {
		for(int i = 0; i<selection.length; i++) {
			try {
				URL url = new URL(selection[i]);
				Image image = ImageIO.read(url);
				addImage(transfer(image));
				mainRunnerCore.lblWsdotstatus.setText("Online.");
			} catch (MalformedURLException e) {
				e.printStackTrace();
				mainRunnerCore.lblWsdotstatus.setText("Offline - Rare MalformedURLException Detected.");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				mainRunnerCore.lblWsdotstatus.setText("Offline - IOException Detected.");
			}catch (NullPointerException e) {
				Image image = Base64EncoderStatic.DecryptPicture(offline);
				addImage(transfer(image));
				mainRunnerCore.lblWsdotstatus.setText("ERROR-NULL");
			}	
			
		}
		imageIcon = imageIconTemp;
		imageIconTemp = new ArrayList<ImageIcon>();
	}
	
	/////////////////////////////////////////////////////////////
	
	public static void main (String args []) {
		loadImage();
	}
	
	public static void refresh() {
		imageIcon = imageIconTemp;
	}
}
