package me.creepinson.modelz.gui;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FilenameUtils;
import org.apache.http.client.utils.URLEncodedUtils;
import org.lwjgl.input.Keyboard;

import me.creepinson.modelz.Modelz;
import me.creepinson.modelz.util.converter.model.Converters.JSONToJava;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;

public class GuiModelz extends GuiScreen {

	private GuiTextField filePath;
	private GuiButton returnMainMenu;
	private GuiButton convert;
	private GuiTextField modelName;
	private GuiTextField packageName;
	private GuiTextField author;
	@Override
	public void initGui() {
		super.initGui();

		this.returnMainMenu = new GuiButton(0, 0, 0, 150, 20, "Return To Main Menu");
		this.addButton(this.returnMainMenu);

		this.convert = new GuiButton(3, 0, 100, 150, 20, "Convert");
		this.addButton(convert);
		this.filePath = new GuiTextField(2, this.fontRenderer, this.width / 2 - 200, this.height / 2 - 46, 237, 20);
		filePath.setMaxStringLength(200);
		filePath.setText("Enter File Path to Convert From");
		this.filePath.setFocused(true);
		
		this.modelName = new GuiTextField(4, this.fontRenderer, this.width / 2 - 200, this.height / 2 - 56, 237, 20);
		modelName.setMaxStringLength(200);
		modelName.setText("Enter Model Name (NO SPACES)");
		this.modelName.setFocused(true);
		
		this.author = new GuiTextField(5, this.fontRenderer, this.width / 2 - 200, this.height / 2 - 66, 237, 20);
		author.setMaxStringLength(200);
		author.setText("Enter Author's Name");
		this.author.setFocused(true);
		
		this.packageName = new GuiTextField(6, this.fontRenderer, this.width / 2 - 200, this.height / 2 - 76, 237, 20);
		packageName.setMaxStringLength(200);
		packageName.setText("Enter Package Name");
		this.packageName.setFocused(true);
		

	}

	protected void keyTyped(char par1, int par2) {
		this.filePath.textboxKeyTyped(par1, par2);
		if (!(par2 == Keyboard.KEY_E && this.filePath.isFocused()))
			try {
				super.keyTyped(par1, par2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.author.textboxKeyTyped(par1, par2);
		if (!(par2 == Keyboard.KEY_E && this.author.isFocused()))
			try {
				super.keyTyped(par1, par2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
			
				e.printStackTrace();
			}
		
		this.modelName.textboxKeyTyped(par1, par2);
		if (!(par2 == Keyboard.KEY_E && this.modelName.isFocused()))
			try {
				super.keyTyped(par1, par2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		this.packageName.textboxKeyTyped(par1, par2);
		if (!(par2 == Keyboard.KEY_E && this.packageName.isFocused()))
			try {
				super.keyTyped(par1, par2);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	@Override
	protected void actionPerformed(GuiButton button) throws IOException {
		super.actionPerformed(button);
		if (button.visible == true) {
			if (button.id == 0) {
				mc.displayGuiScreen(new GuiMainMenu());
			} else if (button.id == 3) {
				String thePath = "file://" + filePath.getText();
//				thePath = URLEncodedUtils.parse(thePath, Charset.forName("UTF-8"));
				URI thingy = null;
				try {
					thingy = new URI(thePath);
				Modelz.getLogger().info(thingy.getAuthority() + thingy.getPath());
				File f = new File(thingy.getAuthority() + thingy.getPath());
				URI u = f.toURI();
				if (f != null && f.exists()) {
					JSONToJava jsonToJava = new JSONToJava(f);
					File export = jsonToJava.convert(FilenameUtils.removeExtension(f.getName()) + ".java", author.getText(), packageName.getText(), modelName.getText(), 0, 0);
					filePath.setText("Exported!");
				} else {
					filePath.setText("Invalid File Path!");
					return;
				}
				}catch (URISyntaxException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
			}
	}

	@Override
	public void updateScreen() {
		super.updateScreen();
		this.filePath.updateCursorCounter();
		this.modelName.updateCursorCounter();
		this.packageName.updateCursorCounter();
		this.author.updateCursorCounter();
		
	}

	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}

	@Override
	public void drawScreen(int par1, int par2, float par3) {
		this.drawDefaultBackground();
		this.filePath.drawTextBox();
		this.modelName.drawTextBox();
		this.packageName.drawTextBox();
		this.author.drawTextBox();
		super.drawScreen(par1, par2, par3);
	}

	@Override
	protected void mouseClicked(int x, int y, int btn) {
		try {
			super.mouseClicked(x, y, btn);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.filePath.mouseClicked(x, y, btn);
		this.modelName.mouseClicked(x, y, btn);
		this.packageName.mouseClicked(x, y, btn);
		this.author.mouseClicked(x, y, btn);
	}

	@Override
	public void onGuiClosed() {
		filePath.setFocused(false);
		modelName.setFocused(false);
		author.setFocused(false);
		packageName.setFocused(false);
		super.onGuiClosed();
	}

}
